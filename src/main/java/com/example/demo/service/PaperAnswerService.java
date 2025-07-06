package com.example.demo.service;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.BatchAnswerRequest;
import com.example.demo.model.PaperAnswer;
import com.example.demo.repository.PaperAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.stream.IntStream;

@Service
public class PaperAnswerService {

    private final PaperAnswerRepository repo;

    public PaperAnswerService(PaperAnswerRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void saveBatchAnswers(BatchAnswerRequest req) {
        // 1) load or create the single row
        PaperAnswer pa = repo.findByUserIdAndPaperId(req.getUserId(), req.getPaperId())
                .orElseGet(() -> {
                    PaperAnswer p = new PaperAnswer();
                    p.setUserId(req.getUserId());
                    p.setPaperId(req.getPaperId());
                    return p;
                });

        try {
            // 2) first, default all 30 answers to "N/A"
            for (int i = 1; i <= 30; i++) {
                Field f = PaperAnswer.class.getDeclaredField("answer" + i);
                f.setAccessible(true);
                f.set(pa, "N/A");
            }

            // 3) then overwrite with whatever the client sent
            for (AnswerDto dto : req.getAnswers()) {
                int q = dto.getQuestionId();
                if (q < 1 || q > 30) {
                    // you might want to throw here instead
                    continue;
                }
                Field f = PaperAnswer.class.getDeclaredField("answer" + q);
                f.setAccessible(true);
                f.set(pa, dto.getAnswerText());
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed mapping answers", e);
        }

        // 4) persist (insert or update)
        repo.save(pa);
    }
}

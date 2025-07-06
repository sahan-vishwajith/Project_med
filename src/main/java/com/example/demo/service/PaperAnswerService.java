package com.example.demo.service;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.BatchAnswerRequest;
import com.example.demo.model.PaperAnswer;
import com.example.demo.repository.PaperAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Map.entry;

@Service
public class PaperAnswerService {

    private final PaperAnswerRepository repo;

    public PaperAnswerService(PaperAnswerRepository repo) {
        this.repo = repo;
    }
    private static final Map<Integer,String> ANSWER_KEY = Map.ofEntries(
            entry(1,  "Frontal, Sphenoid, Ethmoid, Lacrimal, Zygomatic, Maxilla"),
            entry(2,  "Lateral circumflex femoral artery only"),
            entry(3,  "Right lung has 2 lobes; left lung has 3 lobes"),
            entry(4,  ""),
            entry(5,  ""),
            entry(6,  ""),
            entry(7,  ""),
            entry(8,  ""),
            entry(9,  ""),
            entry(10, ""),
            entry(11, ""),
            entry(12, ""),
            entry(13, ""),
            entry(14, ""),
            entry(15, ""),
            entry(16, ""),
            entry(17, ""),
            entry(18, ""),
            entry(19, ""),
            entry(20, ""),
            entry(21, ""),
            entry(22, ""),
            entry(23, ""),
            entry(24, ""),
            entry(25, ""),
            entry(26, ""),
            entry(27, ""),
            entry(28, ""),
            entry(29, ""),
            entry(30, "Only pain and swelling")
    );




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
                String student = dto.getAnswerText().trim();
                String correct = ANSWER_KEY.get(q);
                String status = "Incorrect";
                if (correct != null && correct.equalsIgnoreCase(student)) {
                    status = "Correct";
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

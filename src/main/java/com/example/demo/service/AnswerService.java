package com.example.demo.service;

import com.example.demo.dto.BatchAnswerRequest;
import com.example.demo.model.Answer;
import com.example.demo.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void saveBatchAnswers(BatchAnswerRequest request) {
        List<Answer> answers = request.getAnswers().stream().map(dto -> {
            Answer a = new Answer();
            a.setUserId(request.getUserId());
            a.setQuestionId(dto.getQuestionId());
            a.setAnswerText(dto.getAnswerText());
            return a;
        }).collect(Collectors.toList());

        answerRepository.saveAll(answers);
    }
}
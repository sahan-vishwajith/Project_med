// src/main/java/com/example/demo/dto/BatchAnswerRequest.java
package com.example.demo.dto;

import java.util.List;
import java.util.UUID;

public class BatchAnswerRequest {
    private UUID userId;
    private int paperId;
    private List<AnswerDto> answers;
    // getters / setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public int getPaperId() { return paperId; }
    public void setPaperId(int paperId) { this.paperId = paperId; }
    public List<AnswerDto> getAnswers() { return answers; }
    public void setAnswers(List<AnswerDto> answers) { this.answers = answers; }
}

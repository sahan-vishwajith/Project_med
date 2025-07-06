// src/main/java/com/example/demo/dto/AnswerDto.java
package com.example.demo.dto;

public class AnswerDto {
    private int questionId;
    private String answerText;
    // getters / setters
    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }
    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }
}

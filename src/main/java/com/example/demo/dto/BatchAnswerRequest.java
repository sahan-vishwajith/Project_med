package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BatchAnswerRequest {
    private UUID userId;
    private Long paperId;
    private List<SingleAnswerDTO> answers;


    // getters/setters
}

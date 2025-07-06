// src/main/java/com/example/demo/model/PaperAnswer.java
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
        name = "paper_answers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "paper_id"})
)
@Getter
@Setter
public class PaperAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable=false)
    private UUID userId;

    @Column(name="paper_id", nullable=false)
    private Integer paperId;

    // 30 columns, one per question
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    private String answer6;
    private String answer7;
    private String answer8;
    private String answer9;
    private String answer10;
    private String answer11;
    private String answer12;
    private String answer13;
    private String answer14;
    private String answer15;
    private String answer16;
    private String answer17;
    private String answer18;
    private String answer19;
    private String answer20;
    private String answer21;
    private String answer22;
    private String answer23;
    private String answer24;
    private String answer25;
    private String answer26;
    private String answer27;
    private String answer28;
    private String answer29;
    private String answer30;

    // getters & setters for all fields
    // (omitted here for brevity)
}

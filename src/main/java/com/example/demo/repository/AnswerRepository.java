package com.example.demo.repository;

import com.example.demo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // Optional: define batch operations or upserts here
}
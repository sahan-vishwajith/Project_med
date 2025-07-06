// src/main/java/com/example/demo/repository/PaperAnswerRepository.java
package com.example.demo.repository;

import com.example.demo.model.PaperAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, Long> {
    Optional<PaperAnswer> findByUserIdAndPaperId(UUID userId, Integer paperId);
}

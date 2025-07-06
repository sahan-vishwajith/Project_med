package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Student_t")
public class Student {

    @Id
    private UUID id;

    private String name;

    private LocalDateTime createdAt = LocalDateTime.now();
}

package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public UserResponse createUser(UUID userId, String name) {
        if (repository.existsById(userId)) {
            throw new IllegalArgumentException("User ID already exists");
        }

        Student student = new Student();
        student.setId(userId);
        student.setName(name);
        Student saved = repository.save(student);
        return new UserResponse(saved.getId(), saved.getName(), saved.getCreatedAt());
    }
}

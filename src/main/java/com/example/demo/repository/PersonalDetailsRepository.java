package com.example.demo.repository;

import com.example.demo.model.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, UUID> {
    // no extra methods required
}

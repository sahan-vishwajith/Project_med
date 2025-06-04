package com.example.demo.repository;

import com.example.demo.model.ConsentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ConsentFormRepository extends JpaRepository<ConsentForm, UUID> {
    // Nothing extra needed for a simple save
}

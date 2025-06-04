package com.example.demo.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class PersonalDetailsResponse {
    private UUID id;
    private OffsetDateTime submittedAt;

    public PersonalDetailsResponse(UUID id, OffsetDateTime submittedAt) {
        this.id = id;
        this.submittedAt = submittedAt;
    }

    public UUID getId() {
        return id;
    }
    public OffsetDateTime getSubmittedAt() {
        return submittedAt;
    }
}

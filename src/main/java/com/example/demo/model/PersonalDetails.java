package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "personal_Details_t")
@Setter
@Getter
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private UUID userId;

    @Column(name = "p1", nullable = false)
    private String p1;

    @Column(name = "p2", nullable = false)
    private String p2;

    @Column(name = "p3", nullable = false)
    private String p3;

    @Column(name = "p4", nullable = false)
    private String p4;

    @Column(name = "p5", nullable = false)
    private String p5;

    @Column(name = "p6", nullable = false)
    private String p6;

    @Column(name = "p7", nullable = false)
    private String p7;

    @Column(name = "p8", nullable = false)
    private String p8;

    @Column(name = "p9", nullable = false)
    private String p9;

    @Column(name = "p10", nullable = false)
    private String p10;

    @Column(name = "p11", nullable = false)
    private String p11;

    @Column(name = "p12", nullable = false)
    private String p12;

    @Column(name = "submitted_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime submittedAt = OffsetDateTime.now();
}

package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class CreateUserRequest {
    private UUID userId;
    private String username;
}

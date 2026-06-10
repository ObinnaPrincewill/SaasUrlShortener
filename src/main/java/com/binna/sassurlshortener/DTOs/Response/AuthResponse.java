package com.binna.sassurlshortener.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private UUID userId;
    private String token;
}

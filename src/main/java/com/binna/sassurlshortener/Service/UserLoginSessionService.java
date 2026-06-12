package com.binna.sassurlshortener.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface  UserLoginSessionService {
    void createLoginSession (String sessionId, UUID userId);
    void invalidateSession(UUID userId);
    String getUserSession(UUID userId);

}

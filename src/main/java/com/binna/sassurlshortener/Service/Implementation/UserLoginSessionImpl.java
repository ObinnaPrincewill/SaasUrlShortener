package com.binna.sassurlshortener.Service.Implementation;

import com.binna.sassurlshortener.Service.UserLoginSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserLoginSessionImpl implements UserLoginSessionService {
    @Override
    public void createLoginSession(String sessionId, UUID userId) {

    }

    @Override
    public void invalidateSession(UUID userId) {

    }

    @Override
    public String getUserSession(UUID userId) {
        return "";
    }
}

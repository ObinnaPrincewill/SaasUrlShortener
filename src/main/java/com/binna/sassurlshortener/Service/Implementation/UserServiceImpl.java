package com.binna.sassurlshortener.Service.Implementation;

import com.binna.sassurlshortener.DTOs.Requests.CreateUserRequest;
import com.binna.sassurlshortener.DTOs.Requests.LoginRequest;
import com.binna.sassurlshortener.DTOs.Response.AuthResponse;
import com.binna.sassurlshortener.DTOs.Response.ResponseWrapper;
import com.binna.sassurlshortener.Entity.UserInfo;
import com.binna.sassurlshortener.Repositories.UserRepository;
import com.binna.sassurlshortener.Service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository repository;
    private final PathPatternRequestMatcher.Builder builder;

    @Override
    public ResponseWrapper<AuthResponse> signup(CreateUserRequest payload) {
        UserInfo user = UserInfo.builder()
                .name(payload.getName())
                .email(payload.getEmail())
                .password(payload.getPassword())
                .build();
        UserInfo savedUser = repository.save(user);
        return buildAuthResponse(savedUser.getId(), null, "Sign up successful",
                HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @Override
    public AuthResponse login(LoginRequest payload) {
        return null;
    }

    private ResponseWrapper <AuthResponse> buildAuthResponse
            (UUID id, String token, String message, HttpStatusCode statusCode){
        AuthResponse response = new AuthResponse(id, token);
        return ResponseWrapper.<AuthResponse>builder()
                .data(response)
                .message("message")
                .statusCode(null)
                .build();
    }
}

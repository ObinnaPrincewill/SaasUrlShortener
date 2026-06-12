package com.binna.sassurlshortener.Service.Implementation;

import com.binna.sassurlshortener.Auth.AuthUserDetail;
import com.binna.sassurlshortener.Auth.JwtService;
import com.binna.sassurlshortener.DTOs.Requests.CreateUserRequest;
import com.binna.sassurlshortener.DTOs.Requests.LoginRequest;
import com.binna.sassurlshortener.DTOs.Response.AuthResponse;
import com.binna.sassurlshortener.DTOs.Response.ResponseWrapper;
import com.binna.sassurlshortener.Entity.UserInfo;
import com.binna.sassurlshortener.Exceptions.ResourceNotFoundException;
import com.binna.sassurlshortener.Repositories.UserRepository;
import com.binna.sassurlshortener.Service.UserLoginSessionService;
import com.binna.sassurlshortener.Service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository repository;
    private final PathPatternRequestMatcher.Builder builder;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserLoginSessionImpl sessionService;

    static class UserService {

    }

    @Transactional
    @Override
    public ResponseWrapper<AuthResponse> signup(CreateUserRequest payload) {
        UserInfo user = UserInfo.builder()
                .name(payload.getName())
                .email(payload.getEmail())
                .password(passwordEncoder.encode(payload.getPassword()))
                .roles(payload.getRoles())
                .build();
        UserInfo savedUser = repository.save(user);
        String token = jwtService.generateToken(savedUser.getEmail(), savedUser.getRoles());
        return buildAuthResponse(savedUser.getId(), token, "Sign up successful",
                HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @Override
    public ResponseWrapper<AuthResponse>  login(LoginRequest payload) {
        Optional<UserInfo> userInfoOptional = repository.findByEmail(payload.getEmail());
        if (userInfoOptional.isEmpty())throw new ResourceNotFoundException("User not found");
        UserInfo user = userInfoOptional.get();
        String token = jwtService.generateToken(user.getEmail(), user.getRoles());
        String sessionId = LocalDateTime.now().toString();
        sessionService.createLoginSession(sessionId, user.getId());
        return buildAuthResponse(user.getId(), token, "Login successful",
                HttpStatusCode.valueOf(HttpStatus.OK.value()));
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
    public void  logout(LoginRequest payload) {
        AuthUserDetail auth = (AuthUserDetail) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Optional <UserInfo> loggedInUser = repository.findByEmail(auth.getUsername());
        if (loggedInUser.isEmpty()) throw new ResourceNotFoundException("User Not Found");
        sessionService.invalidateSession(loggedInUser.get().getId());
    }

}

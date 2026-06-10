package com.binna.sassurlshortener.Service;

import com.binna.sassurlshortener.DTOs.Requests.CreateUserRequest;
import com.binna.sassurlshortener.DTOs.Requests.LoginRequest;
import com.binna.sassurlshortener.DTOs.Response.AuthResponse;
import com.binna.sassurlshortener.DTOs.Response.ResponseWrapper;

public interface UserServiceInterface {
    ResponseWrapper<AuthResponse> signup (CreateUserRequest payload);
    AuthResponse login (LoginRequest payload);
}

package com.binna.sassurlshortener.DTOs.Requests;

import com.binna.sassurlshortener.ENUMS.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private Set<Roles> roles;
}

package com.binna.sassurlshortener.DTOs.Requests;

import com.binna.sassurlshortener.ENUMS.Roles;
import jakarta.persistence.Column;

import java.util.Set;

public class CreateaUserRequest {
    private String name;
    private String email;
    private String password;
    private Set<Roles> roles;
}

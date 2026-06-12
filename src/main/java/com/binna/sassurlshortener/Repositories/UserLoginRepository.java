package com.binna.sassurlshortener.Repositories;

import com.binna.sassurlshortener.Entity.UserLoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserLoginRepository extends JpaRepository <UserLoginSession, UUID> {
    Optional findByUserId (UUID userId);
}

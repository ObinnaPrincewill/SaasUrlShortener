package com.binna.sassurlshortener.Repositories;

import com.binna.sassurlshortener.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <UserInfo, UUID> {
    Optional<UserInfo> findByEmail(String email);
}

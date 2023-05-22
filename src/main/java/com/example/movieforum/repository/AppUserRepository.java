package com.example.movieforum.repository;

import com.example.movieforum.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByUsername(String username);
}
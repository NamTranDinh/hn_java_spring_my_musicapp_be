package com.aptech.mymusic.domain.repository;

import com.aptech.mymusic.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :username ")
    Optional<User> getUserByUsername(String username);
}

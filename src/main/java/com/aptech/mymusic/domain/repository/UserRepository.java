package com.aptech.mymusic.domain.repository;

import com.aptech.mymusic.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.username = ?2")
    int updatePasswordByUsername(String password, String username);

    @Query("SELECT u FROM User u WHERE u.username = :username ")
    Optional<User> getUserByUsername(String username);
}

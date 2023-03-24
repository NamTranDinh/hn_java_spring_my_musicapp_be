package com.aptech.mymusic.presentation.service;

import com.aptech.mymusic.domain.entities.User;
import com.aptech.mymusic.domain.entities.UserDetail;
import com.aptech.mymusic.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return new UserDetail(user.get());
    }
}


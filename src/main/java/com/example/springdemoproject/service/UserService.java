package com.example.springdemoproject.service;

import com.example.springdemoproject.model.User;
import com.example.springdemoproject.model.UserDto;
import com.example.springdemoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.username())) {
            throw new IllegalStateException("Username already exists");
        }

        User user = User.builder()
            .username(userDto.username())
            .password(userDto.password())
            .build();

        User savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(), savedUser.getUsername(), savedUser.getPassword());
    }
}

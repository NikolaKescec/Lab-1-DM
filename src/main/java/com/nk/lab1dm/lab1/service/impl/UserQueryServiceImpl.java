package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.exception.ResourceNotFoundException;
import com.nk.lab1dm.lab1.repository.UserRepository;
import com.nk.lab1dm.lab1.service.UserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}

package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.repository.UserRepository;
import com.nk.lab1dm.lab1.service.UserCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

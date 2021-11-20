package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.security.UserPrincipal;
import com.nk.lab1dm.lab1.service.UserDetailsService;
import com.nk.lab1dm.lab1.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userService.findByEmail(email);

        return UserPrincipal.create(user);    }

    @Override
    public UserDetails loadUserById(String id) {
        final User user = userService.findById(id);

        return UserPrincipal.create(user);
    }
}

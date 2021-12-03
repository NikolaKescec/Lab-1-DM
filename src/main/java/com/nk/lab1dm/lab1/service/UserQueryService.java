package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.entity.User;

public interface UserQueryService {

    User findByEmail(String email);

    User findById(String id);

}

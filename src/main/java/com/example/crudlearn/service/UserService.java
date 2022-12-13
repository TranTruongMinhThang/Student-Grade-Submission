package com.example.crudlearn.service;

import com.example.crudlearn.entity.User;

public interface UserService {
    User saveUser(User user);
    User getUser(long id);
    User getUser(String username);
}

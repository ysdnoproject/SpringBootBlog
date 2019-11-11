package com.example.demo.service;

import com.example.demo.doma.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findByName(String name);

    int insert(User user);
}

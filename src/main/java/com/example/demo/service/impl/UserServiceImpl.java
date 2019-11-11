package com.example.demo.service.impl;

import com.example.demo.doma.dao.UserDao;
import com.example.demo.doma.entity.User;
import com.example.demo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @NonNull
    private UserDao userDao;

    @NonNull
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userDao.selectByUsername(name);
    }

    @Override
    public int insert(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.insert(user);
    }
}

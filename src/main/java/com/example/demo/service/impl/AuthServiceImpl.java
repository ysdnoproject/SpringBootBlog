package com.example.demo.service.impl;

import com.example.demo.doma.dao.UserDao;
import com.example.demo.doma.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserDetailsService {
    @NonNull
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || "".equals(username)) {
            throw new UsernameNotFoundException("ユーザーIDが未入力です");
        }

        Optional<User> user = dao.selectByUsername(username);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException("ユーザーIDが不正です。");
        }

        return user.get();
    }
}

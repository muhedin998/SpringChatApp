package com.alibou.websocket.services.Impl;

import com.alibou.websocket.models.AppUser;
import com.alibou.websocket.repositories.UserRepository;
import com.alibou.websocket.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AppUser createUser(AppUser user) {
        return  userRepository.save(user);
    }

    @Override
    public AppUser getUserByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public AppUser deleteUser(AppUser user) {
        return null;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}

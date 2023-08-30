package com.alibou.websocket.services;

import com.alibou.websocket.models.AppUser;

import java.util.List;

public interface UserService {

    AppUser createUser(AppUser user);

    AppUser getUserByPhone(String phoneNumber);

    AppUser deleteUser(AppUser user);

    List<AppUser> getAllUsers();

}

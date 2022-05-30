package com.dray.dating.repository.impl;

import com.dray.dating.models.User;
import com.dray.dating.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    Map<String, User> userIdToUserMap;

    public UserRepositoryImpl() {
        userIdToUserMap = new HashMap<>();
    }

    @Override
    public User createUser(User user) {
        userIdToUserMap.putIfAbsent(user.getUserId(), user);
        return user;
    }

    @Override
    public User deleteUser(String userId) {
        return userIdToUserMap.remove(userId);
    }

    @Override
    public User getUserById(String userId) {
        return userIdToUserMap.get(userId);
    }

    @Override
    public List<User> getUsers() {
        return List.copyOf(userIdToUserMap.values());
    }
}

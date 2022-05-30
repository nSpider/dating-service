package com.dray.dating.repository;

import com.dray.dating.models.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User deleteUser(String userId);

    User getUserById(String userId);

    List<User> getUsers();
}

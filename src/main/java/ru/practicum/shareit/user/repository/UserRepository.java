package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User removeUserById(Long userId);

    void updateUser(User user);
}

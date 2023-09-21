package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceDaoImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtotoUser(userDto);
        User newUser = repository.createUser(user);
        log.info("{} has been created", userDto);
        return userMapper.userToUserDto(newUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : repository.getAllUsers()) {
            usersDto.add(userMapper.userToUserDto(user));
        }
        log.info("Get all user");
        return usersDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = repository.getUserById(id);
        log.info("User with id = {} is uploaded", id);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        User user = repository.getUserById(id);
        userDto.setId(id);
        if (userDto.getName() != null && !userDto.getName().isBlank()) {
            user.setName(userDto.getName());
        } else {
            userDto.setName(user.getName());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
            repository.updateUser(userMapper.userDtotoUser(userDto));
            user.setEmail(userDto.getEmail());
        }
        log.info("User with id = {} is updated", user.getId());
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto removeUserById(Long id) {
        User user = repository.removeUserById(id);
        log.info("User with id = {} removed", id);
        return userMapper.userToUserDto(user);
    }
}

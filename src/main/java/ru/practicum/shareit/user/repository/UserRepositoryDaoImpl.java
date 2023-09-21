package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.DataAlreadyExistException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
@Slf4j
public class UserRepositoryDaoImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private final Set emailUniqSet = new HashSet<>();
    private Long id = 0L;

    @Override
    public User createUser(User user) {
        final String email = user.getEmail();
        if (emailUniqSet.contains(email)) {
            throw new DataAlreadyExistException("Email: " + email + " already exists");
        }
        user.setId(++id);
        users.put(user.getId(), user);
        emailUniqSet.add(email);
        log.info("User with email = {}  has been added to Repository", user.getEmail());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Get all user from Repository");
        return List.copyOf(users.values());
    }

    @Override
    public User getUserById(Long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException("User with id =" + id + "doesn't exist");
        }
        log.info("User with id = {} is uploaded from Repository", id);
        return users.get(id);
    }

    @Override
    public User removeUserById(Long id) {
        User memoryUser = getUserById(id);
        emailUniqSet.remove(memoryUser.getEmail());
        users.remove(id);
        log.info("User with id = {} removed", id);
        return memoryUser;
    }

    public void updateUser(User user) {
        final String email = user.getEmail();
        users.computeIfPresent(user.getId(), (id, u) -> {
                    if (!email.equals(u.getEmail())) {
                        if (emailUniqSet.contains(email)) {
                            throw new DataAlreadyExistException("Email: " + email + " already exists");
                        }
                        emailUniqSet.remove(u.getEmail());
                        emailUniqSet.add(email);
                    }
                    return user;
                }
        );
    }
}

package ru.kaiko.rehospital.service;

import org.springframework.stereotype.Service;
import ru.kaiko.rehospital.domain.User;
import ru.kaiko.rehospital.repo.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findByUsername(String name) {
        return userRepo.findByUsername(name);
    }
}

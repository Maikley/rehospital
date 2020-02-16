package ru.kaiko.rehospital.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaiko.rehospital.domain.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
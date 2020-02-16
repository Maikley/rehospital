package ru.kaiko.rehospital.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaiko.rehospital.domain.Complain;

public interface ComplainRepo extends CrudRepository<Complain, Long> {
    Iterable<Complain> findAllByOrderByIdDesc();
}

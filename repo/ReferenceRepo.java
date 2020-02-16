package ru.kaiko.rehospital.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaiko.rehospital.domain.Reference;

public interface ReferenceRepo extends CrudRepository<Reference, Long> {
}

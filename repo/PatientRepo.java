package ru.kaiko.rehospital.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaiko.rehospital.domain.Patient;

public interface PatientRepo extends CrudRepository<Patient, Long> {

    Iterable<Patient> findAllByFirstNameAndLastName(String firstName,
                                                    String lastName);

}

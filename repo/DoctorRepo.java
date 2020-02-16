package ru.kaiko.rehospital.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kaiko.rehospital.domain.Disease;
import ru.kaiko.rehospital.domain.Doctor;

public interface DoctorRepo extends CrudRepository<Doctor, Long> {
    @Query(value = "SELECT d FROM Doctor d WHERE :dis MEMBER OF d.diseases")
    Iterable<Doctor> findAllByDiseases(@Param("dis") Disease diseases);
}
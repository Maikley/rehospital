package ru.kaiko.rehospital.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kaiko.rehospital.domain.Disease;
import ru.kaiko.rehospital.domain.Doctor;
import ru.kaiko.rehospital.domain.Patient;
import ru.kaiko.rehospital.domain.Record;

public interface RecordRepo extends CrudRepository<Record, Long> {
// TODO - it doesn't work
//    @Query(value = "SELECT r FROM Record r WHERE r.doctor IN (SELECT d FROM Doctor d WHERE :dis MEMBER OF d.diseases) AND r.user IS NULL")
//    Iterable<Record> findAllByDiseases(@Param("dis") Disease diseases);

    Iterable<Record> findAllByDoctor(Doctor doctor);
    Iterable<Record> findAllByPatient(Patient patient);
}
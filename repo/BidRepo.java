package ru.kaiko.rehospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kaiko.rehospital.domain.Bid;
import ru.kaiko.rehospital.domain.Disease;
import ru.kaiko.rehospital.domain.Doctor;

public interface BidRepo extends CrudRepository<Bid, Long> {

//    @Modifying
//    @Query(value = "Update Bid b SET b.patient=:patient WHERE b.id=:bid")
//    void updatePatient(@Param("bid") Long bid, @Param("patient") Long patient);
}
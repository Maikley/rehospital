package ru.kaiko.rehospital.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaiko.rehospital.domain.Report;

public interface ReportRepo extends CrudRepository<Report, Long> {
}

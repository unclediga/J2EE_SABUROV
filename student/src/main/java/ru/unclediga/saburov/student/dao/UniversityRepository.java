package ru.unclediga.saburov.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.unclediga.saburov.student.domain.University;

/* not required, for info only (see StudentRepository)*/
@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}

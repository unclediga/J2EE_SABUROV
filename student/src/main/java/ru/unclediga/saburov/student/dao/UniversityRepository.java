package ru.unclediga.saburov.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.unclediga.saburov.student.domain.University;

import java.util.List;

/* not required, for info only (see StudentRepository)*/
@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    @Query(name = "University.findAllFull",
            value = "SELECT u FROM University u LEFT JOIN FETCH u.faculties")
    List<University> findAllFull();
}

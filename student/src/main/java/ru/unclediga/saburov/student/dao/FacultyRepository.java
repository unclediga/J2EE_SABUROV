package ru.unclediga.saburov.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unclediga.saburov.student.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}

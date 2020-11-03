package ru.unclediga.saburov.student.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unclediga.saburov.student.dao.FacultyRepository;
import ru.unclediga.saburov.student.dao.UniversityRepository;
import ru.unclediga.saburov.student.domain.Faculty;
import ru.unclediga.saburov.student.domain.University;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @Transactional(readOnly = true)
    public List<University> findUniversities() {
        return universityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Faculty> findFaculties() {
        return facultyRepository.findAll();
    }
}

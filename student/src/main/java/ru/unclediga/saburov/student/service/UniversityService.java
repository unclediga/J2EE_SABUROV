package ru.unclediga.saburov.student.service;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unclediga.saburov.student.dao.FacultyRepository;
import ru.unclediga.saburov.student.dao.UniversityRepository;
import ru.unclediga.saburov.student.domain.Faculty;
import ru.unclediga.saburov.student.domain.University;

import java.util.List;
import java.util.Optional;

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
    public List<University> findFullUniversities() {
        return universityRepository.findAllFull();
    }

    @Transactional(readOnly = true)
    public List<Faculty> findFaculties() {
        return facultyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Faculty getFaculty(Long id) {
        final Optional<Faculty> opt = facultyRepository.findById(id);
        final Faculty faculty = opt.get();
        // 1 Query
        // LazyInitializationException on access Lazy-field in others methods,
        // where this method being called
        faculty.getUniversity().getUniversityName();
        return faculty;
    }

    @Transactional(readOnly = true)
    public Faculty getFacultyGoodStyle(Long id) {
        final Optional<Faculty> opt = facultyRepository.findById(id);
        final Faculty faculty = opt.get();
        // 2 Query
        // No LazyInitializationException on Lazy-field
        // And also no exceptions in others methods, where this method being called
        // ex. @Test and etc
        Hibernate.initialize(faculty.getUniversity());
        return faculty;
    }

    @Transactional(readOnly = true)
    public Faculty getFacultyChildStyle(Long id) {
        final Optional<Faculty> opt = facultyRepository.findById(id);
        final Faculty faculty = opt.get();
        // 2 Query
        // No LazyInitializationException on Lazy-field
        // And also no exceptions in others methods, where this method being called
        // ex. @Test and etc
        // implicit proxy use
        faculty.getUniversity().getUniversityName();
        return faculty;
    }
}

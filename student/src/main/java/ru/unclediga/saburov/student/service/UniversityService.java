package ru.unclediga.saburov.student.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unclediga.saburov.student.dao.UniversityRepository;
import ru.unclediga.saburov.student.domain.University;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository repository;

    @Transactional(readOnly = true)
    public List<University> findUniversity() {
        return repository.findAll();
    }
}

package ru.unclediga.saburov.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unclediga.saburov.student.dao.StudentRepository;
import ru.unclediga.saburov.student.domain.Student;
import ru.unclediga.saburov.student.domain.StudentDocument;
import ru.unclediga.saburov.student.view.StudentRequest;
import ru.unclediga.saburov.student.view.StudentResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository repository;

    public void simpleCall() {
        LOGGER.info("StudentService was CALLED!!!");
    }

    @Transactional
    public List<StudentResponse> getStudentInfo(StudentRequest request) {
        List<Student> student = repository.findStudent(
                request.getLastName(),
                request.getFirstName(),
                request.getModdleName(),
                request.getDateOfBirth(),
                request.getPassportSeria(),
                request.getPassportNumber(),
                request.getPassportDate());
        if (student.isEmpty()) {
            return Collections.emptyList();
        }
        final List<StudentDocument> documents = student.get(0).getDocuments();
        return documents.stream().map(this::getResponse).collect(Collectors.toList());
    }

    private StudentResponse getResponse(StudentDocument doc) {
        StudentResponse response = new StudentResponse();
        response.setDocumentNumber(doc.getDocumentNumber());
        response.setDocumentDate(doc.getDocumentDate());
        response.setExpiredDate(doc.getExpiredDate());
        response.setFacultyName(doc.getFaculty().getFacultyName());
        response.setUniversityName(doc.getFaculty().getUniversity().getUniversityName());
        response.setStudentForm(doc.getStudentForm().toString());

        return response;
    }
}

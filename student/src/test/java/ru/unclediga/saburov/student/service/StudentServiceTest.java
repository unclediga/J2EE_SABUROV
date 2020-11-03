package ru.unclediga.saburov.student.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.unclediga.saburov.student.view.StudentRequest;
import ru.unclediga.saburov.student.view.StudentResponse;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class StudentServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentService studentService;

    @Test
    public void studentInfo() {
        final StudentRequest request = new StudentRequest();
        request.setFirstName("First");
        request.setLastName("Last");
        request.setMiddleName("Middle");
        request.setDateOfBirth(LocalDate.of(2000,4,12));
        request.setPassportSeria("1111");
        request.setPassportNumber("222222");
        request.setPassportDate(LocalDate.of(2016,4,30));
        final List<StudentResponse> list = studentService.getStudentInfo(request);
        Assert.assertTrue(list.size() > 0);
    }
}
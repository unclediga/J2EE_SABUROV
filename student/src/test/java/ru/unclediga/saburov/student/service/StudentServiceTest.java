package ru.unclediga.saburov.student.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class StudentServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentService studentService;

    @Test
    public void simpleCall() {
        studentService.simpleCall();
        LOGGER.info("StudentServiceTest was DONE!");
    }
}
package ru.unclediga.saburov.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.unclediga.saburov.student.service.StudentService;
import ru.unclediga.saburov.student.view.StudentRequest;
import ru.unclediga.saburov.student.view.StudentResponse;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentResponse> getStudentInfo(StudentRequest request) {
        return studentService.getStudentInfo(request);
    }

    @RequestMapping("/check")
    public String checkAdmin() {
        return "REST Service is working!";
    }
}

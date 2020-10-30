package ru.unclediga.saburov.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.unclediga.saburov.student.service.StudentService;
import ru.unclediga.saburov.student.view.StudentRequest;
import ru.unclediga.saburov.student.view.StudentResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentResponse> getStudentInfo(StudentRequest request) {
        return studentService.getStudentInfo(request);
    }
}

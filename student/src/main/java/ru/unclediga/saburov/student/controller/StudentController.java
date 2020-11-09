package ru.unclediga.saburov.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.unclediga.saburov.student.service.StudentService;
import ru.unclediga.saburov.student.view.StudentRequest;
import ru.unclediga.saburov.student.view.StudentResponse;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentResponse> getStudentInfo(@RequestBody StudentRequest request) {
        return studentService.getStudentInfo(request);
    }

    @RequestMapping("/check")
    public String checkAdmin() {
        return "REST Service is working!";
    }

    @GetMapping(path = "/params/{checkId}")
    public String checkParams(@PathVariable("checkId") Long pathVar, @RequestParam("comment") String reqParam) {
        return pathVar + ":" + reqParam;
    }

    @GetMapping(path = "/params2/{p1}/{p2}")
    public String checkParams2(@PathVariable("p1") Long p1,
                               @PathVariable(value = "p2", required = false) Long p2) {
        return String.format("[%d]:[%d]", p1, p2);
    }

}

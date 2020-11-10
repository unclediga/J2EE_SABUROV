package ru.unclediga.saburov.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.unclediga.saburov.student.service.StudentService;
import ru.unclediga.saburov.student.view.StudentRequest;
import ru.unclediga.saburov.student.view.StudentResponse;

import java.io.IOException;
import java.io.InputStream;
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

    @PostMapping(value = "/phototest1")
    public String fileUploadTest1(@RequestParam("comment") String comment) {
        return "fileUploadTest1 : param Comment = " + comment;
    }

    @PostMapping(value = "/phototest2", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadTest2(@RequestParam("comment") String comment) {
        return "fileUploadTest2 : param Comment = " + comment;
    }

    @PostMapping(value = "/phototest3", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadTest3(@RequestParam("comment") String comment,
                                  @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) {
        return "fileUploadTest3 : param Comment = " + comment  + " photo = " + photoFile;
    }

    @PostMapping(value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("comment") String comment,
                             @RequestParam("photoFile") MultipartFile photoFile) {
        try (InputStream is = photoFile.getInputStream()) {
            return "Comment:" + comment + " Name:" + photoFile.getName() +
                    " file name:" + photoFile.getOriginalFilename() +
                    " avg size:" + is.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

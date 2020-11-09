package ru.unclediga.saburov.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.unclediga.saburov.student.domain.University;
import ru.unclediga.saburov.student.service.UniversityService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/universityList")
public class UniversityListController {

    @Autowired
    private UniversityService service;

    @GetMapping
    public String fingUniversities(Model model) {
        final List<University> list = service.findUniversities();
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.addAttribute("universities", list);
        model.addAttribute("isFromMVC",true);
        return "universityList";
    }
}

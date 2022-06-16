package ru.dispenker.kontinuum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dispenker.kontinuum.models.Student;
import ru.dispenker.kontinuum.repo.StudentsRepository;

@Controller
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;

    @PostMapping("/add/student")
    public String homeAddDistribution(
            @RequestParam String studentName,
            Model model) {

        Student student = new Student(studentName);
        studentsRepository.save(student);

        return "redirect:/add";
    }

    @PostMapping("/delete/student")
    public String homeDeleteDistribution(
            @RequestParam Long studentId,
            Model model) {

        Student student = studentsRepository.findById(studentId).orElseThrow();
        studentsRepository.delete(student);

        return "redirect:/add";
    }
}

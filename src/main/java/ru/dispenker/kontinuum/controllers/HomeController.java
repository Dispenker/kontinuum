package ru.dispenker.kontinuum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dispenker.kontinuum.models.Student;
import ru.dispenker.kontinuum.models.StudentGroup;
import ru.dispenker.kontinuum.repo.ActivityRepository;
import ru.dispenker.kontinuum.repo.DistributionRepository;
import ru.dispenker.kontinuum.repo.StudentsGroupRepository;
import ru.dispenker.kontinuum.repo.StudentsRepository;
import ru.dispenker.kontinuum.utilits.PersonPoint;
import ru.dispenker.kontinuum.utilits.PointCalculator;

import java.sql.Date;
import java.util.HashMap;

@Controller
public class HomeController {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private StudentsGroupRepository studentsGroupRepository;

    @Autowired
    private DistributionRepository distributionRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", null);
        model.addAttribute("title", "Home");
        model.addAttribute("firstDate", "2021-10-18");
        model.addAttribute("secondDate", "2021-10-24");
        return "home";
    }

    @PostMapping("/")
    public String homeGet(
            @RequestParam Date firstDate,
            @RequestParam Date secondDate,
            Model model) {

        HashMap<Long, PersonPoint> points = PointCalculator.getStudentsAtDate(distributionRepository, activityRepository, studentsRepository, firstDate, secondDate);
        model.addAttribute("students", PointCalculator.entriesSortedByValues(points));
        model.addAttribute("firstDate", firstDate);
        model.addAttribute("secondDate", secondDate);
        return "home";
    }

    @GetMapping("/add")
    public String homeAdd(Model model) {
        Iterable<StudentGroup> studentsGroup = studentsGroupRepository.findAll();
        Iterable<Student> students = studentsRepository.findAll();
        model.addAttribute("groups", studentsGroup);
        model.addAttribute("students", students);
        if (model.getAttribute("date") == null) {
            model.addAttribute("date", "2021-09-27");
        }
        model.addAttribute("title", "Добавление значений");
        model.addAttribute("display", "none");
        return "home-add";
    }
}

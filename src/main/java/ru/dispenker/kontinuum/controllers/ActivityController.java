package ru.dispenker.kontinuum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dispenker.kontinuum.models.Activity;
import ru.dispenker.kontinuum.models.Student;
import ru.dispenker.kontinuum.models.StudentGroup;
import ru.dispenker.kontinuum.repo.ActivityRepository;
import ru.dispenker.kontinuum.repo.DistributionRepository;
import ru.dispenker.kontinuum.repo.StudentsGroupRepository;
import ru.dispenker.kontinuum.repo.StudentsRepository;
import ru.dispenker.kontinuum.utilits.DataFinder;

import java.sql.Date;

@Controller
public class ActivityController {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private StudentsGroupRepository studentsGroupRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private DistributionRepository distributionRepository;

    public String homeAdd(Model model, Date date, String warning) {
        Iterable<StudentGroup> studentsGroup = studentsGroupRepository.findAll();
        Iterable<Student> students = studentsRepository.findAll();
        model.addAttribute("groups", studentsGroup);
        model.addAttribute("students", students);
        model.addAttribute("date", date);
        model.addAttribute("title", "Добавление значений");
        model.addAttribute("warning", warning);
        model.addAttribute("display", "block");
        return "home-add";
    }

    @PostMapping("/add/activity")
    public String homeAddActivity(
            @RequestParam Date date,
            @RequestParam Long groupId,
            @RequestParam Long studentId,
            @RequestParam int activity,
            @RequestParam int homework,
            Model model) {

        if (!DataFinder.checkStudentAtGroup(distributionRepository, groupId, studentId)) {
            return homeAdd(model, date, "Студент не в этой группе");
        }

        Activity activity1 = DataFinder.getActivity(activityRepository, date, groupId, studentId);
        activity1.setActivity(activity);
        activity1.setHomework(homework);
        activityRepository.save(activity1);

        return "redirect:/add";
    }

    @PostMapping("/delete/activity")
    public String homeDeleteActivity(
            @RequestParam Date date,
            @RequestParam Long groupId,
            @RequestParam Long studentId,
            Model model) {

        Activity activity1 = DataFinder.getActivity(activityRepository, date, groupId, studentId);
        activityRepository.delete(activity1);

        return "redirect:/add";
    }
}

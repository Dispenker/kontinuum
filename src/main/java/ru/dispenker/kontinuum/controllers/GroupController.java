package ru.dispenker.kontinuum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dispenker.kontinuum.models.StudentGroup;
import ru.dispenker.kontinuum.repo.StudentsGroupRepository;

@Controller
public class GroupController {

    @Autowired
    private StudentsGroupRepository studentsGroupRepository;

    @PostMapping("/add/group")
    public String homeAddDistribution(
            @RequestParam String groupName,
            Model model) {

        StudentGroup group = new StudentGroup(groupName);
        studentsGroupRepository.save(group);

        return "redirect:/add";
    }

    @PostMapping("/delete/group")
    public String homeDeleteDistribution(
            @RequestParam Long groupId,
            Model model) {

        StudentGroup group = studentsGroupRepository.findById(groupId).orElseThrow();
        studentsGroupRepository.delete(group);

        return "redirect:/add";
    }
}

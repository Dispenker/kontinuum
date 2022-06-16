package ru.dispenker.kontinuum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dispenker.kontinuum.models.Distribution;
import ru.dispenker.kontinuum.repo.DistributionRepository;
import ru.dispenker.kontinuum.utilits.DataFinder;

@Controller
public class DistributionController {

    @Autowired
    private DistributionRepository distributionRepository;

    @PostMapping("/add/distribution")
    public String homeAddDistribution(
            @RequestParam Long groupId,
            @RequestParam Long studentId,
            @RequestParam int result,
            Model model) {

        Distribution distribution = DataFinder.getDistribution(distributionRepository, groupId, studentId);
        distribution.setLastResult(result);
        distributionRepository.save(distribution);

        return "redirect:/add";
    }

    @PostMapping("/delete/distribution")
    public String homeDeleteDistribution(
            @RequestParam Long groupId,
            @RequestParam Long studentId,
            Model model) {

        Distribution distribution = DataFinder.getDistribution(distributionRepository, groupId, studentId);
        distributionRepository.delete(distribution);

        return "redirect:/add";
    }
}

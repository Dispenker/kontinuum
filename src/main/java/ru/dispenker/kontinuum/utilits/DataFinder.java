package ru.dispenker.kontinuum.utilits;

import ru.dispenker.kontinuum.models.Activity;
import ru.dispenker.kontinuum.models.Distribution;
import ru.dispenker.kontinuum.repo.ActivityRepository;
import ru.dispenker.kontinuum.repo.DistributionRepository;

import java.sql.Date;

public class DataFinder {

    public static Activity getActivity(ActivityRepository activityRepos, Date date, Long idGroup, Long idStudent) {
        for (Activity activity : activityRepos.findAll()) {
            if (activity.getDate().equals(date)) {
                if (activity.getIdGroup() == idGroup) {
                    if (activity.getIdStudent() == idStudent) {
                        return activityRepos.findById(activity.getId()).orElseThrow();
                    }
                }
            }
        }

        return new Activity(date, idGroup, idStudent);
    }

    public static Distribution getDistribution(DistributionRepository distributionRepo, Long idGroup, Long idStudent) {
        for (Distribution distribution : distributionRepo.findAll()) {
            if (distribution.getIdGroup() == idGroup) {
                if (distribution.getIdStudent() == idStudent) {
                    return distributionRepo.findById(distribution.getId()).orElseThrow();
                }
            }
        }

        return new Distribution(idGroup, idStudent);
    }

    public static boolean checkStudentAtGroup(DistributionRepository distributionRepo, Long groupId, Long studentId) {
        for (Distribution distribution : distributionRepo.findAll()) {
            if (distribution.getIdGroup() == groupId) {
                if (distribution.getIdStudent() == studentId) {
                    return true;
                }
            }
        }

        return false;
    }


}

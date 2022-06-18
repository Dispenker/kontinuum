package ru.dispenker.kontinuum.utilits;

import ru.dispenker.kontinuum.models.Activity;
import ru.dispenker.kontinuum.models.Distribution;
import ru.dispenker.kontinuum.repo.ActivityRepository;
import ru.dispenker.kontinuum.repo.DistributionRepository;

import java.sql.Date;

public class DataFinder {

    public static Activity getActivity(ActivityRepository activityRepos, Date date, Long idGroup, Long idStudent) {
        Activity activity = activityRepos.findByDateAndIdGroupAndIdStudent(date, idGroup, idStudent);

        return (activity != null) ? activity : new Activity(date, idGroup, idStudent);
    }

    public static Distribution getDistribution(DistributionRepository distributionRepo, Long idGroup, Long idStudent) {
        Distribution distribution = distributionRepo.findByIdGroupAndIdStudent(idGroup, idStudent);

        return (distribution != null) ? distribution : new Distribution(idGroup, idStudent);
    }

    public static boolean checkStudentAtGroup(DistributionRepository distributionRepo, Long idGroup, Long idStudent) {
        Distribution distribution = distributionRepo.findByIdGroupAndIdStudent(idGroup, idStudent);

        return distribution != null;
    }


}

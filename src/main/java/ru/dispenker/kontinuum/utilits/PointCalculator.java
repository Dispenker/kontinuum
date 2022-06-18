package ru.dispenker.kontinuum.utilits;

import ru.dispenker.kontinuum.models.Activity;
import ru.dispenker.kontinuum.repo.ActivityRepository;
import ru.dispenker.kontinuum.repo.DistributionRepository;
import ru.dispenker.kontinuum.repo.StudentsRepository;

import java.sql.Date;
import java.util.*;

public class PointCalculator {

    private PointCalculator() { }

    public static HashMap<Long, PersonPoint> getStudentsAtDate(DistributionRepository distributionRepo, ActivityRepository activityRepo, StudentsRepository studentsRep, Date firstDate, Date secondDate) {
        List<Activity> activities = activityRepo.findAllByDateBetween(firstDate, secondDate); // Получение всех записей в промежутке времени
        HashMap<Long, PersonPoint> persons = new HashMap<>(); // Заполнение списка студентов

        for (Activity activity : activities) { // Перебор записей и расчет баллов
            PersonPoint person = persons.getOrDefault(activity.getIdStudent(), new PersonPoint(
                    activity.getIdStudent(),
                    studentsRep.findById(activity.getIdStudent()).orElseThrow().getName()));
            person.setPoints(calculate(
                    activity.getActivity(),
                    activity.getHomework(),
                    distributionRepo.findByIdGroupAndIdStudent(activity.getIdGroup(), activity.getIdStudent()).getLastResult()));
            persons.put(activity.getIdStudent(), person);
        }

        return persons;
    }

    public static TreeSet<PersonPoint> entriesSortedByValues(HashMap<Long, PersonPoint> map) { // Для сортировки по баллам
        TreeSet<PersonPoint> sortedEntries = new TreeSet<>((e1, e2) -> {
            int res = e1.compareTo(e2);
            return res != 0 ? res : 1;
        });
        sortedEntries.addAll(map.values());
        return sortedEntries;
    }

    public static float calculate(int activity, int homework, int lastResult) { // Формула расчета баллов для одного дня
        return activityConverter(activity) + homeworkConverter(homework) + 0.02f * lastResult;
    }

    private static float activityConverter (int activity) { // Условия для активности
        if (activity >= 4) {
            return 1f;
        } else if (activity >= 2) {
            return 0.5f;
        }

        return 0f;
    }

    private static float homeworkConverter (int homework) { // Условия для процента выполнения ДЗ
        if (homework == 100) {
            return 3f;
        } else if (homework >= 80) {
            return 2f;
        } else if (homework >= 50) {
            return 1f;
        } else if (homework < 30) {
            return -0.5f;
        }

        return 0f;
    }
}

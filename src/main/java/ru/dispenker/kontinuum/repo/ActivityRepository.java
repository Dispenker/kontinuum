package ru.dispenker.kontinuum.repo;

import org.springframework.data.repository.CrudRepository;
import ru.dispenker.kontinuum.models.Activity;

import java.sql.Date;
import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Activity findByDateAndIdGroupAndIdStudent(Date date, Long idGroup, Long idStudent);
    List<Activity> findAllByDateBetween(Date firstDate, Date secondDate);
}

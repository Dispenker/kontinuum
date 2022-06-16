package ru.dispenker.kontinuum.repo;

import org.springframework.data.repository.CrudRepository;
import ru.dispenker.kontinuum.models.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
}

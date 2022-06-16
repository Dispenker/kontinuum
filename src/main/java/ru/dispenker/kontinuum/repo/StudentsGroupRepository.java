package ru.dispenker.kontinuum.repo;

import org.springframework.data.repository.CrudRepository;
import ru.dispenker.kontinuum.models.StudentGroup;

public interface StudentsGroupRepository extends CrudRepository<StudentGroup, Long> {
}

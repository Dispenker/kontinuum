package ru.dispenker.kontinuum.repo;

import org.springframework.data.repository.CrudRepository;
import ru.dispenker.kontinuum.models.Student;

public interface StudentsRepository extends CrudRepository<Student, Long> {

}

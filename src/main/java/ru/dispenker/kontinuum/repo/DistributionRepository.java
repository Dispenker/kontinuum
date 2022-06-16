package ru.dispenker.kontinuum.repo;

import org.springframework.data.repository.CrudRepository;
import ru.dispenker.kontinuum.models.Distribution;

public interface DistributionRepository extends CrudRepository<Distribution, Long> {
}

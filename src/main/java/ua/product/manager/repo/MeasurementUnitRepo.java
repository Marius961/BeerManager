package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.MeasurementUnit;

public interface MeasurementUnitRepo extends CrudRepository<MeasurementUnit, Long> {

    boolean existsByShortName(String name);

}

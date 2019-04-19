package ua.product.manager.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.MeasurementUnit;

import java.util.Optional;

public interface MeasurementUnitRepo extends CrudRepository<MeasurementUnit, Long> {

    boolean existsByShortName(String shortName);

    boolean existsByFullName(String fullName);

    boolean existsByFullNameOrShortName(String fullName, String shortName);

    boolean existsByFullNameOrShortNameAndIdNot(String fullName, String shortName, Long id);

    Optional<MeasurementUnit> findFirstByFullNameAndShortName(String fullName, String shortName);
}

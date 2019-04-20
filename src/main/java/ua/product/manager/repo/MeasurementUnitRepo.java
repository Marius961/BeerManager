package ua.product.manager.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.product.manager.entities.MeasurementUnit;

import java.util.Optional;

public interface MeasurementUnitRepo extends CrudRepository<MeasurementUnit, Long> {

    boolean existsByShortName(String shortName);

    boolean existsByFullName(String fullName);

    boolean existsByFullNameOrShortName(String fullName, String shortName);

    @Query("SELECT  count(m) FROM MeasurementUnit m WHERE (m.shortName = :shortName OR m.fullName = :fullName) AND m.id <> :id")
    Integer countByShortNameAndFullNameAndNotId(String shortName, String fullName, Long id);
}

package ua.product.manager.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.product.manager.entities.Category;
import ua.product.manager.entities.Subcategory;

@Repository
public interface SubcategoryRepo extends CrudRepository<Subcategory, Long> {

    boolean existsByName(String name);

    @Query("SELECT count(sc) FROM Subcategory sc WHERE sc.name = :name AND sc.id <> :id")
    Integer countByNameAndIdNot(String name, Long id);
}

package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.product.manager.entities.Category;
import ua.product.manager.entities.Subcategory;

@Repository
public interface SubcategoryRepo extends CrudRepository<Subcategory, Long> {

    boolean existsByName(String name);

    Iterable<Subcategory> findAllByCategory(Category category);
}

package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.product.manager.entities.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {

    boolean existsByName(String name);
}

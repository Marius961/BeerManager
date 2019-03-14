package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.Subcategory;

public interface ProductRepo extends CrudRepository<Product, Long> {

    boolean existsByName(String name);

    Iterable<Product> findAllBySubcategory(Subcategory subcategory);
}

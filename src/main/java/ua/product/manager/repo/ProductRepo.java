package ua.product.manager.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.Subcategory;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    boolean existsByName(String name);

    boolean existsBySubcategoryId(Long subcategoryId);
}

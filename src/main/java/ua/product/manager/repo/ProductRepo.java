package ua.product.manager.repo;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.Subcategory;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    boolean existsByName(String name);

    boolean existsBySubcategoryId(Long subcategoryId);

    boolean existsByMeasurementUnitId(Long measurementUnitId);

    Page<Product> findBySellerUserId(Long id, Pageable pageable);

    Optional<Product> findBySellerUserIdAndId(Long userId, Long productId);

}

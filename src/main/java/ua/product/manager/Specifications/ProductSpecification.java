package ua.product.manager.Specifications;

import org.springframework.data.jpa.domain.Specification;
import ua.product.manager.entities.Product;

public class ProductSpecification {

    public static Specification<Product> productsByCategoryId(Long id) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("subcategory").get("id"), id);
        };
    }

    public static Specification<Product> productsByMinPrice(double price) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("priceForMeasurementUnit"), price);
        };
    }

    public static Specification<Product> productsByMaxPrice(double price) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("priceForMeasurementUnit"), price);
        };
    }
}


package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.OrderedItem;
import ua.product.manager.entities.ShippingAddress;

public interface OrderedItemRepo extends CrudRepository<OrderedItem, Long> {


    boolean existsByProductId(Long id);
}

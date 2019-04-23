package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import ua.product.manager.entities.ShippingAddress;

public interface ShippingAddressRepo extends CrudRepository<ShippingAddress, Long> {

    Iterable<ShippingAddress> findAllByUserId(Long userId);

    boolean existsByIdAndUserId(Long addressId, Long userId);

    void deleteByIdAndUserId(Long addressId, Long userId);
}

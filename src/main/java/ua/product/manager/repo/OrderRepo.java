package ua.product.manager.repo;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.product.manager.entities.Order;


public interface OrderRepo extends PagingAndSortingRepository<Order, Long> {

    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}

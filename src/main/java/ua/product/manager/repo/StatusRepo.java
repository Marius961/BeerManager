package ua.product.manager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.product.manager.entities.Status;

import java.util.Optional;

@Repository
public interface StatusRepo extends CrudRepository<Status, Long> {

    Optional<Status> findByCode(String code);
}

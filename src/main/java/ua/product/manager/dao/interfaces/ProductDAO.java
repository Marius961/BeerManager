package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductById(int productId);

    void insertProduct(Product product);

    @Secured("ROLE_ADMIN")
    void updateProduct(Product product);

    @Secured("ROLE_ADMIN")
    void removeProduct(int productId);

    @Secured("ROLE_ADMIN")
    void blockProduct(int productId);

    @Secured("ROLE_ADMIN")
    void unblockProduct(int productId);
}

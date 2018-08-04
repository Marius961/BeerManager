package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDAO {

    @Secured("ROLE_ADMIN")
    List<Product> getAllProducts();

    @Secured("ROLE_ADMIN")
    List<Product> getActiveProducts();

    @Secured("ROLE_ADMIN")
    List<Product> getNotActiveProducts();

    @Secured("ROLE_ADMIN")
    Product getProductById(int productId);

    @Secured("ROLE_ADMIN")
    List<Product> searchProducts(String request);

    @Secured("ROLE_ADMIN")
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

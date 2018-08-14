package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductById(int productId);

    void insertProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(int productId);

    void blockProduct(int productId);

    void unblockProduct(int productId);
}

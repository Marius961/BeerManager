package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.OrderDAO;
import ua.product.manager.dao.interfaces.ProductDAO;
import ua.product.manager.dao.interfaces.UserDAO;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;
import ua.product.manager.models.Product;
import ua.product.manager.services.interfaces.OrderService;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;

    @Autowired
    private void setDAOs(OrderDAO orderDAO, ProductDAO productDAO, UserDAO userDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void blockProduct(int productId) {
    }

    @Override
    public void unblockProduct(int productId) {
    }

    @Override
    public void removeProduct(int productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public void addProduct(Product product) {
    }

}

package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.OrderDAO;
import ua.product.manager.dao.interfaces.ProductDAO;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;
import ua.product.manager.services.interfaces.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    @Autowired
    private void setDAOs(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    @Override
    public void createOrder(Order order) {

    }


    @Override
    public Order getOrderByUserId(int userId) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId) {
        List<Order> orders = orderDAO.getOrdersByUserId(userId);
        if (orders.size() != 0) {
            for (Order order : orders) {
                setOrderItems(order);
            }
        }
        return orders;
    }

    private void setOrderItems(Order order) {
        order.setOrderItems(orderDAO.getItemsByOrderId(order.getId()));
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setProduct(productDAO.getProductById(orderItem.getProductId()));
        }
    }
}

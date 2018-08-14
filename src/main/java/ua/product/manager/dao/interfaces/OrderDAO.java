package ua.product.manager.dao.interfaces;

import org.springframework.security.access.annotation.Secured;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;

import java.util.List;

public interface OrderDAO {

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrdersByUserNameAndDate(String username,String date, int limit);

    List<Order> getOrdersByUserNameExceptDate(String username, String date, int limit);

    List<OrderItem> getItemsByOrderId(int orderId);

    List<Order> getOrdersByDate(String date, int limit);

    List<Order> getOrdersExceptDate(String date, int limit);

    void addItemToOrder(OrderItem orderItem);

    void deleteItem(int itemId);

    Order getOrder(int orderId);

    int createOrder(Order order);

    void updateOrder(Order order);

    void removeOrder(int orderId);
}

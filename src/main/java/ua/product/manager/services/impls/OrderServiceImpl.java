package ua.product.manager.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.dao.interfaces.OrderDAO;
import ua.product.manager.dao.interfaces.ProductDAO;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;
import ua.product.manager.models.Product;
import ua.product.manager.services.interfaces.OrderService;

import java.util.*;

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
        int orderId = orderDAO.createOrder(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getVolume() > 0) {
                orderItem.setOrderId(orderId);
                orderDAO.addItemToOrder(orderItem);
            }
        }
    }


    @Override
    public Order getOrderByUserId(int userId) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                setOrderItems(order);
            }
        }
        return orders;
    }

    @Override
    public Map<String, List<Order>> getOrdersByUserId(int userId) {
        List<Order> orders = orderDAO.getOrdersByUserId(userId);
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                setOrderItems(order);
            }
        }
        return groupOrdersByDate(orders);
    }

    @Override
    public Map<String, List<Order>> getOrdersByUsername(String username) {
        List<Order> orders = orderDAO.getOrdersByUserName(username);
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                setOrderItems(order);
            }
        }
        return groupOrdersByDate(orders);
    }

    @Override
    public Order getNewOrder() {
        Order order = new Order();
        List<Product> allProducts = productDAO.getAllProducts();
        List<OrderItem> tempItems = new LinkedList<>();
        for (Product product : allProducts) {
            if (product.isActive()) {
                tempItems.add(new OrderItem(product.getId(), product));
            }
        }
        order.setOrderItems(tempItems);
        return order;
    }

    @Override
    public void removeOrder(int orderId) {
        orderDAO.removeOrder(orderId);
    }

    private void setOrderItems(Order order) {
        order.setOrderItems(orderDAO.getItemsByOrderId(order.getId()));
        for (OrderItem item : order.getOrderItems()) {
            Product product = productDAO.getProductById(item.getProductId());
            item.setProduct(product);
        }
    }

    private Map<String, List<Order>> groupOrdersByDate(List<Order> orders) {
        Map<String, List<Order>> groupedOrders = new HashMap<>();
        String date;
        for (Order order : orders) {
            date = order.getExecDate();
            if (!groupedOrders.containsKey(date)) {
                List<Order> tempLost = new ArrayList<>();
                tempLost.add(order);
                groupedOrders.put(date, tempLost);
            } else {
                groupedOrders.get(date).add(order);
            }
        }
        return new TreeMap<>(groupedOrders).descendingMap();
    }
}

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
        int orderId = orderDAO.createOrder(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            if (orderItem.getVolume() > 0) {
                orderItem.setOrderId(orderId);
                orderDAO.addItemToOrder(orderItem);
            }
        }
    }

    @Override
    public Map<String, List<Order>> getOrdersWithUserDataByDate(String date) {
        List<Order> orders;
        Date currentDate = new Date();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        Pattern p = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");
        Matcher m = p.matcher(date);
        if (m.matches()) {
            orders = orderDAO.getOrdersByDate(date, 9999);
        } else if (date.equals("!CURRENT_DATE")) {
            orders = orderDAO.getOrdersExceptDate(formattedDate, 50);
        } else if (date.equals("CURRENT_DATE")){
            orders = orderDAO.getOrdersByDate(formattedDate, 9999);
        } else {
            orders = new ArrayList<>();
        }
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                setOrderItems(order);
                order.setCustomer(userDAO.getUserById(order.getUserId()));
            }
        }
        return groupOrdersByDate(orders);
    }

    @Override
    public Map<String, List<Order>> getOrdersByUsernameAndDate(String username, String date) {
        List<Order> orders;
        Date currentDate = new Date();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
        Pattern p = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");
        Matcher m = p.matcher(date);
        if (m.matches()) {
            orders = orderDAO.getOrdersByUserNameAndDate(username, date, 9999);
        } else if (date.equals("!CURRENT_DATE")) {
            orders = orderDAO.getOrdersByUserNameExceptDate(username, formattedDate, 50);
        } else if (date.equals("CURRENT_DATE")){
            orders = orderDAO.getOrdersByUserNameAndDate(username, formattedDate, 9999);
        } else {
            orders = new ArrayList<>();
        }
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
    public void blockProduct(int productId) {
        productDAO.blockProduct(productId);
    }

    @Override
    public void unblockProduct(int productId) {
        productDAO.unblockProduct(productId);
    }

    @Override
    public void removeProduct(int productId) {
        productDAO.removeProduct(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public void addProduct(Product product) {
        productDAO.insertProduct(product);
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

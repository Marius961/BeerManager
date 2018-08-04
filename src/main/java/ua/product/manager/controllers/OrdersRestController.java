package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.containers.SimpleStringContainer;
import ua.product.manager.models.Order;
import ua.product.manager.models.Product;
import ua.product.manager.services.interfaces.OrderService;
import ua.product.manager.services.interfaces.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrdersRestController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    private void setServices(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<Order>>> getOrders(Principal principal) {
        Map<String, List<Order>> orders = orderService.getOrders(principal.getName());
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-orders/{id}", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<Order>>> getOrdersByUserId(@PathVariable int id) {
        Map<String, List<Order>> orders = orderService.getOrders(id);
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-orders", method = RequestMethod.POST ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<Order>>> getAllOrders(@RequestBody SimpleStringContainer date) {
        Map<String, List<Order>> orders = orderService.getOrdersWithUserDataByDate(date.getStr());
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = orderService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        if (product != null) {
            orderService.addProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/product/{statusCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable int statusCode) {
        List<Product> products;
        if (statusCode == 0) {
            products = orderService.getNotActiveProducts();
        } else {
            products = orderService.getActiveProducts();
        }
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product-block/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> blockProduct(@PathVariable int id) {
        orderService.blockProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/product-unblock/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> unblockProduct(@PathVariable int id) {
        orderService.unblockProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/product-remove/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeProduct(@PathVariable int id) {
        orderService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/product-search", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> searchProduct(@RequestBody SimpleStringContainer request) {
        List<Product> products = orderService.searchProduct(request.getStr());
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

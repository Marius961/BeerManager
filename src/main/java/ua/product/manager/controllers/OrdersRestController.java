package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.containers.SimpleStringContainer;
import ua.product.manager.models.Order;
import ua.product.manager.services.interfaces.OrderService;
import ua.product.manager.services.interfaces.UserService;

import java.security.Principal;
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
}

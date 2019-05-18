package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.Order;
import ua.product.manager.entities.OrderStatus;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.services.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> getUserOrders(
            @RequestParam(name = "p") int page,
            @RequestParam(name = "s") int size
    ) {
        return orderService.getUserOrders(page, size);
    }

    @GetMapping("/received")
    public Page<Order> getReceivedOrders(
            @RequestParam(name = "p") int page,
            @RequestParam(name = "s") int size
    ) {
        return orderService.getReceivedOrders(page, size);
    }


    @PostMapping
    public void createOrder(@Valid @RequestBody Order order) throws NotFoundException {
        orderService.createOrder(order);
    }

    @PostMapping("/add-status")
    public void addOrderStatus(@Valid @RequestBody OrderStatus orderStatus) throws NotFoundException {
        orderService.addOrderStatus(orderStatus);
    }

}

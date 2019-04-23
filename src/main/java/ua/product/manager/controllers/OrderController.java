package ua.product.manager.controllers;

import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.Order;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.services.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Iterable<Order> getUserOrders(
            @RequestParam(name = "p") int page,
            @RequestParam(name = "s") int size
    ) {
        return orderService.getUserOrders(page, size);
    }

    @PostMapping
    public void createOrder(@Valid @RequestBody Order order) throws NotFoundException {
        orderService.createOrder(order);
    }

}

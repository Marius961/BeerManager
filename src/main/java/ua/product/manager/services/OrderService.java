package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.*;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

@Service
public class OrderService {

    private OrderRepo orderRepo;
    private ProductRepo productRepo;
    private UserService userService;
    private CartItemRepo cartItemRepo;
    private SellerRepo sellerRepo;
    private StatusRepo statusRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, ProductRepo productRepo, UserService userService, CartItemRepo cartItemRepo, SellerRepo sellerRepo, StatusRepo statusRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userService = userService;
        this.cartItemRepo = cartItemRepo;
        this.sellerRepo = sellerRepo;
        this.statusRepo = statusRepo;
    }

    @Transactional
    public void createOrder(Order order) throws NotFoundException {
        if (!order.getOrderedItems().isEmpty()) {
            double orderTotalPrice = 0;
            Long firstSellerId = null;
            int index = 0;
            for (OrderedItem item : order.getOrderedItems()) {
                Optional<Product> opCurrentProduct = productRepo.findById(item.getProduct().getId());
                if (opCurrentProduct.isPresent()) {
                    Product currentProduct = opCurrentProduct.get();
                    if (index == 0) {
                        firstSellerId = currentProduct.getSeller().getId();
                        order.setSeller(currentProduct.getSeller());
                    }
                    if (Objects.equals(firstSellerId, currentProduct.getSeller().getId())) {
                        if (item.getQuantity() > 0) {
                            item.setId(null);
                            double itemTotalPrice = currentProduct.getPriceForMeasurementUnit() * item.getQuantity();
                            item.setTotalPrice(itemTotalPrice);
                            orderTotalPrice += itemTotalPrice;
                            index++;
                        } else throw new IllegalArgumentException("Quantity of item with id " + item.getId() + " must be more than 0");
                    } else throw new IllegalArgumentException("Products in order must be from the same seller");
                } else throw new NotFoundException("You can not add not existed product to order");
            }
            order.setId(null);
            order.setCanceled(false);
            order.setCreationDate(new Date());
            order.setTotalPrice(orderTotalPrice);
            order.setUser((User) userService.loadUserByUsername(getPrincipal().getName()));

            Optional<Status> opStatus = statusRepo.findByCode("0");

            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setOrder(order);
            orderStatus.setStatusSetDate(new Date());
            orderStatus.setStatus(opStatus.orElse(null));
            Set<OrderStatus> statuses = new HashSet<>();
            statuses.add(orderStatus);
            order.setStatuses(statuses);
            orderRepo.save(order);
            removeOrderedItemsFromCart(order.getOrderedItems());
        } else throw new NotFoundException("You can not create empty order");
    }

    private void removeOrderedItemsFromCart(List<OrderedItem> orderedItems) {
        for (OrderedItem orderedItem : orderedItems) {
            cartItemRepo.deleteByProductId(orderedItem.getProduct().getId());
        }
    }

    public Page<Order> getUserOrders(int page, int size) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return orderRepo.findAllByUserId(user.getId(), PageRequest.of(page, size));
    }

    public Page<Order> getReceivedOrders(int page, int size) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return orderRepo.findAllBySellerUserId(user.getId(), PageRequest.of(page, size));
    }

    public Iterable<Order> getAllOrders(int page, int size) {
        return orderRepo.findAll(PageRequest.of(page, size));
    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}

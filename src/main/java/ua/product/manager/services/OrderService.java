package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
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

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final UserService userService;
    private final CartItemRepo cartItemRepo;
    private final StatusRepo statusRepo;
    private final ShippingAddressRepo shippingAddressRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, ProductRepo productRepo, UserService userService, CartItemRepo cartItemRepo, StatusRepo statusRepo, ShippingAddressRepo shippingAddressRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userService = userService;
        this.cartItemRepo = cartItemRepo;
        this.statusRepo = statusRepo;
        this.shippingAddressRepo = shippingAddressRepo;
    }

    @Transactional
    public void createOrder(Order order) throws NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<ShippingAddress> opAddress = shippingAddressRepo.findByUserIdAndId(user.getId(), order.getUserAddressId());
        if (!order.getOrderedItems().isEmpty() && opAddress.isPresent()) {
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
                        index++;
                    }
                    if (Objects.equals(firstSellerId, currentProduct.getSeller().getId())) {
                        if (item.getQuantity() > 0) {
                            item.setId(null);
                            double itemTotalPrice = currentProduct.getPriceForMeasurementUnit() * item.getQuantity();
                            item.setTotalPrice(itemTotalPrice);
                            orderTotalPrice += itemTotalPrice;

                            incrementProductOrderCount(currentProduct);
                        } else throw new IllegalArgumentException("Quantity of item with id " + item.getId() + " must be more than 0");
                    } else throw new IllegalArgumentException("Products in order must be from the same seller");
                } else throw new NotFoundException("You can not add not existed product to order");
            }
            order.setId(null);
            order.setCanceled(false);
            order.setCreationDate(new Date());
            order.setTotalPrice(orderTotalPrice);
            order.setUser(user);
            order.setAddress(new OrderAddress(opAddress.get(), order));

            Optional<Status> opStatus = statusRepo.findByCode("CREATED");

            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setOrder(order);
            orderStatus.setStatusSetDate(new Date());
            orderStatus.setStatus(opStatus.orElse(null));
            Set<OrderStatus> statuses = new HashSet<>();
            statuses.add(orderStatus);
            order.setStatuses(statuses);
            orderRepo.save(order);
            removeOrderedItemsFromCart(order.getOrderedItems());
        } else throw new NotFoundException("You can not create empty order. Or address not specified.");
    }

    @Transactional
    public void removeOrderedItemsFromCart(List<OrderedItem> orderedItems) {
        for (OrderedItem orderedItem : orderedItems) {
            cartItemRepo.deleteByProductId(orderedItem.getProduct().getId());
        }
    }

    public Page<Order> getUserOrders(int page, int size) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return orderRepo.findAllByUserId(user.getId(), PageRequest.of(page, size, Sort.by("creationDate").descending()));
    }

    public Page<Order> getReceivedOrders(int page, int size) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return orderRepo.findAllBySellerUserId(user.getId(), PageRequest.of(page, size));
    }

    @Transactional
    public void addOrderStatus(OrderStatus orderStatus) throws NotFoundException {
        // check for status existing
        Optional<Status> opStatus = statusRepo.findByCode(orderStatus.getStatus().getCode());
        if (opStatus.isPresent()) {

            // check for order existing
            Optional<Order> opOrder = orderRepo.findById(orderStatus.getOrder().getId());
            if (opOrder.isPresent()) {
                Order currentOrder = opOrder.get();

                User user = (User) userService.loadUserByUsername(getPrincipal().getName());
                // is current user can add new status to order
                boolean isUserCanAddStatus = user.getId().equals(currentOrder.getUserId()) || user.getId().equals(currentOrder.getSeller().getUser().getId());

                // getting current order status
                OrderStatus currentStatus = currentOrder.getStatuses().stream().findFirst().orElseThrow(() -> new NotFoundException("Cannot find first status"));
                // is current user seller or recipient
                if (isUserCanAddStatus) {

                    boolean isUserSeller = user.getId().equals(currentOrder.getSeller().getUser().getId());
                    Status newStatus = opStatus.get();

                    // created empty order status object
                    OrderStatus newOrderStatus = new OrderStatus();

                    if (isUserSeller) {
                        switch (currentStatus.getStatus().getCode()) {
                            case ("CREATED"):
                                if (newStatus.getCode().equals("CONFIRMED") || newStatus.getCode().equals("REJECTED")) {
                                    newOrderStatus.setStatus(newStatus);
                                    break;
                                }
                            case ("CONFIRMED"):
                                if (newStatus.getCode().equals("SHIPPED_OUT")) {
                                    newOrderStatus.setStatus(newStatus);
                                    break;
                                }
                        }
                    } else {
                        switch (currentStatus.getStatus().getCode()) {
                            case ("SHIPPED_OUT"):
                                if (newStatus.getCode().equals("COMPLETED")) {
                                    newOrderStatus.setStatus(newStatus);
                                    break;
                                }
                            case ("CREATED"):
                                if (newStatus.getCode().equals("CANCELED")) {
                                    newOrderStatus.setStatus(newStatus);
                                    break;
                                }
                        }
                    }

                    if (newOrderStatus.getStatus() != null) {
                        newOrderStatus.setOrder(currentOrder);
                        newOrderStatus.setStatusSetDate(new Date());
                        newOrderStatus.setStatusComment(orderStatus.getStatusComment());
                        currentOrder.addOrderStatus(newOrderStatus);
                        orderRepo.save(currentOrder);
                    } else throw new NotFoundException("Cannot find status which can be added to this order");
                } else throw new AccessDeniedException("You can nod add status to this order");
            } else throw new NotFoundException("Cannot find order with id " + orderStatus.getOrder().getId());
        } else throw new NotFoundException("Cannot add status which not exists");
    }

    @Transactional
    public void incrementProductOrderCount(Product product) {
        product.setOrdersCount(product.getOrdersCount() + 1);
        productRepo.save(product);
    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}

package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.CartItem;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.CartItemRepo;
import ua.product.manager.repo.ProductRepo;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
public class CartService {

    private CartItemRepo cartItemRepo;
    private UserService userService;
    private ProductRepo productRepo;

    @Autowired
    public CartService(CartItemRepo cartItemRepo, UserService userService, ProductRepo productRepo) {
        this.cartItemRepo = cartItemRepo;
        this.userService = userService;
        this.productRepo = productRepo;
    }

    public Iterable<CartItem> getCartItems() {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return cartItemRepo.findAllByUserId(user.getId());
    }

    public void setAddToOrder(Long itemId, boolean isAddToOrder) throws NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<CartItem> opCartItem = cartItemRepo.findByIdAndUserId(itemId, user.getId());
        if (opCartItem.isPresent()) {
            CartItem cartItem = opCartItem.get();
            cartItem.setAddToOrder(isAddToOrder);
            cartItemRepo.save(cartItem);
        } else throw new NotFoundException("Cannot find cart item with id " + itemId);
    }

    public void changeQuantity(Long itemId, int quantity) throws NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<CartItem> opCartItem = cartItemRepo.findByIdAndUserId(itemId, user.getId());
        if (opCartItem.isPresent()) {
            if (quantity >= 1) {
                CartItem cartItem = opCartItem.get();
                cartItem.setQuantity(quantity);
                cartItemRepo.save(cartItem);
            } else throw new IllegalArgumentException("Cart item quantity must be 1 or more");
        } else throw new NotFoundException("Cannot find cart item with id " + itemId);
    }

    @Transactional
    public void addCartItem(CartItem cartItem) throws NotFoundException {
        if (cartItem.getQuantity() >= 1) {
            User user = (User) userService.loadUserByUsername(getPrincipal().getName());
            Optional<CartItem> opCartItem = cartItemRepo.findByProductIdAndUserId(cartItem.getProduct().getId(),user.getId());
            if (opCartItem.isPresent()) {
                CartItem updatedCartItem = opCartItem.get();
                updatedCartItem.setQuantity(updatedCartItem.getQuantity() + cartItem.getQuantity());
                cartItemRepo.save(updatedCartItem);
            } else if (productRepo.existsById(cartItem.getProduct().getId())) {
                cartItem.setUser(user);
                cartItemRepo.save(cartItem);
            } else throw new NotFoundException("Cannot add product to cart. Product with id " + cartItem.getProduct().getId() + " not found");
        } else throw new IllegalArgumentException("Cart item quantity must be 1 or more");

    }


    @Transactional
    public void deleteCartItem(Long cartItemId) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        cartItemRepo.deleteByIdAndUserId(cartItemId, user.getId());
    }


    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

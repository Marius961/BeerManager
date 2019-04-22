package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.CartItem;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.services.CartService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Iterable<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping
    public void addCartItem(@Valid @RequestBody CartItem cartItem) throws NotFoundException {
        cartService.addCartItem(cartItem);
    }

    @PutMapping("/{itemId}/{quantity}")
    public void changeQuantity(@PathVariable Long itemId, @PathVariable int quantity) throws NotFoundException {
        cartService.changeQuantity(itemId, quantity);
    }

    @PutMapping("/add-to-order/{itemId}/{isAddToOrder}")
    public void setAddItemToOrder(@PathVariable Long itemId, @PathVariable boolean isAddToOrder) throws NotFoundException {
        cartService.setAddToOrder(itemId, isAddToOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
    }


}

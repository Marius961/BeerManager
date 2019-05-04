package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.ShippingAddress;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.services.ShippingAddressService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/shipping-address")
public class ShippingAddressController {

    private ShippingAddressService shippingAddressService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @PostMapping
    public void addShippingAddress(@Valid @RequestBody ShippingAddress shippingAddress) {
        shippingAddressService.addShippingAddress(shippingAddress);
    }

    @PutMapping
    public void updateShippingAddress(@Valid @RequestBody ShippingAddress shippingAddress) throws NotFoundException {
        shippingAddressService.updateShippingAddress(shippingAddress);
    }

    @GetMapping
    public Iterable<ShippingAddress> getUserShippingAddresses() {
        return shippingAddressService.getUserShippingAddresses();
    }

    @GetMapping("/{id}")
    public ShippingAddress getUserShippingAddress(@PathVariable Long id) throws NotFoundException {
        return shippingAddressService.getUserShippingAddress(id);
    }


    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        shippingAddressService.deleteAddress(id);
    }
}

package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.ShippingAddress;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.ShippingAddressRepo;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
public class ShippingAddressService {

    private ShippingAddressRepo shippingAddressRepo;
    private UserService userService;

    @Autowired
    public ShippingAddressService(ShippingAddressRepo shippingAddressRepo, UserService userService) {
        this.shippingAddressRepo = shippingAddressRepo;
        this.userService = userService;
    }

    public void addShippingAddress(ShippingAddress shippingAddress) {
        shippingAddress.setId(null);
        shippingAddress.setUser((User) userService.loadUserByUsername(getPrincipal().getName()));
        shippingAddressRepo.save(shippingAddress);
    }

    public void updateShippingAddress(ShippingAddress shippingAddress) throws NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        if (shippingAddressRepo.existsByIdAndUserId(shippingAddress.getId(), user.getId())) {
            shippingAddressRepo.save(shippingAddress);
        } else throw new NotFoundException("Cannot find address with id " + shippingAddress.getId());
    }


    public Iterable<ShippingAddress> getUserShippingAddresses() {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return shippingAddressRepo.findAllByUserId(user.getId());
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        shippingAddressRepo.deleteByIdAndUserId(addressId, user.getId());
    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

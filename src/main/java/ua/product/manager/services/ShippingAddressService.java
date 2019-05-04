package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.ShippingAddress;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.OrderRepo;
import ua.product.manager.repo.ShippingAddressRepo;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
public class ShippingAddressService {

    private ShippingAddressRepo shippingAddressRepo;
    private UserService userService;
    private OrderRepo orderRepo;

    @Autowired
    public ShippingAddressService(ShippingAddressRepo shippingAddressRepo, UserService userService, OrderRepo orderRepo) {
        this.shippingAddressRepo = shippingAddressRepo;
        this.userService = userService;
        this.orderRepo = orderRepo;
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

    public ShippingAddress getUserShippingAddress(Long id) throws NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<ShippingAddress> opAddress = shippingAddressRepo.findByUserIdAndId(user.getId(), id);
        if (opAddress.isPresent()) {
            return opAddress.get();
        } else throw new NotFoundException("Cannot find address with id " + id);
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        if (!orderRepo.existsByAddressId(addressId)) {
            shippingAddressRepo.deleteByIdAndUserId(addressId, user.getId());
        } else throw new IllegalArgumentException("Cannot delete address which already in use");
    }

    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.Subcategory;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.ProductRepo;
import ua.product.manager.repo.SubcategoryRepo;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private ProductImageService productImageService;
    private UserService userService;
    private SubcategoryRepo subcategoryRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductImageService productImageService, UserService userService, SubcategoryRepo subcategoryRepo) {
        this.productRepo = productRepo;
        this.productImageService = productImageService;
        this.userService = userService;
        this.subcategoryRepo = subcategoryRepo;
    }

    public void addProduct(MultipartFile file, Product product, Principal principal) throws IOException, ObjectExistException {
        if (!productRepo.existsByName(product.getName())) {
            product.setImageName(productImageService.saveImage(file));
            User user = (User) userService.loadUserByUsername(principal.getName());
            product.setUser(user);
            productRepo.save(product);
        } else throw new ObjectExistException("Product with name " + product.getName() + " already exist");
    }

    public Iterable<Product> getAllBySubcategory(Long subcategoryId) throws NotFoundException {
        Optional<Subcategory> opSubcategory = subcategoryRepo.findById(subcategoryId);
        if (opSubcategory.isPresent()) {
            return productRepo.findAllBySubcategory(opSubcategory.get());
        } else throw new NotFoundException("Cannot find subcategory with id " + subcategoryId);
    }
}

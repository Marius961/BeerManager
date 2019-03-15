package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.Subcategory;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.MeasurementUnitRepo;
import ua.product.manager.repo.ProductRepo;
import ua.product.manager.repo.SubcategoryRepo;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private ImgService imgService;
    private UserService userService;
    private SubcategoryRepo subcategoryRepo;
    private MeasurementUnitRepo measurementUnitRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, ImgService imgService, UserService userService, SubcategoryRepo subcategoryRepo, MeasurementUnitRepo measurementUnitRepo) {
        this.productRepo = productRepo;
        this.imgService = imgService;
        this.userService = userService;
        this.subcategoryRepo = subcategoryRepo;
        this.measurementUnitRepo = measurementUnitRepo;
    }

    public void addProduct(MultipartFile file, Product product, Principal principal) throws IOException, ObjectExistException {
        boolean isProductNameExist = isProductExist(product.getName());
        boolean isMeasurementUnitExist = measurementUnitRepo.existsById(product.getMeasurementUnit().getId());
        if (!isProductNameExist && isMeasurementUnitExist) {
            product.setImageName(imgService.saveImage(file));
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

    public boolean isProductExist(String name) {
        return productRepo.existsByName(name);
    }
}

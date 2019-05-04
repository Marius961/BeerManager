package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.Product;
import ua.product.manager.entities.Seller;
import ua.product.manager.entities.Subcategory;
import ua.product.manager.entities.User;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.*;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static ua.product.manager.Specifications.ProductSpecification.*;

@Service
public class ProductService {

    private ProductRepo productRepo;
    private ImgService imgService;
    private UserService userService;
    private SubcategoryRepo subcategoryRepo;
    private MeasurementUnitRepo measurementUnitRepo;
    private SellerRepo sellerRepo;
    private OrderedItemRepo orderedItemRepo;
    private CartItemRepo cartItemRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, ImgService imgService, UserService userService, SubcategoryRepo subcategoryRepo, MeasurementUnitRepo measurementUnitRepo, SellerRepo sellerRepo, OrderedItemRepo orderedItemRepo, CartItemRepo cartItemRepo) {
        this.productRepo = productRepo;
        this.imgService = imgService;
        this.userService = userService;
        this.subcategoryRepo = subcategoryRepo;
        this.measurementUnitRepo = measurementUnitRepo;
        this.sellerRepo = sellerRepo;
        this.orderedItemRepo = orderedItemRepo;
        this.cartItemRepo = cartItemRepo;
    }

    public void addProduct(MultipartFile file, Product product, Principal principal) throws IOException, ObjectExistException {
        boolean isProductNameExist = isProductExist(product.getName());
        boolean isMeasurementUnitExist = measurementUnitRepo.existsById(product.getMeasurementUnit().getId());
        if (!isProductNameExist && isMeasurementUnitExist) {
            product.setImageName(imgService.saveImage(file));
            User user = (User) userService.loadUserByUsername(principal.getName());
            Optional<Seller> opSeller = sellerRepo.findByUserId(user.getId());
            if (opSeller.isPresent()) {
                Seller seller = opSeller.get();
                product.setSeller(seller);
                seller.getProducts().add(product);
                sellerRepo.save(seller);
            } else {
                Seller newSeller = new Seller();
                newSeller.setUser(user);
                newSeller.setName(user.getUsername());
                Set<Product> products = new HashSet<>();
                product.setSeller(newSeller);
                products.add(product);

                newSeller.setProducts(products);
                sellerRepo.save(newSeller);
            }
        } else throw new ObjectExistException("Product with name " + product.getName() + " already exist");
    }

    public void updateProduct(MultipartFile file, Product product) throws IOException, NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<Product> opProduct = productRepo.findBySellerUserIdAndId(user.getId(), product.getId());
        if (opProduct.isPresent()) {
            Product currentProduct = opProduct.get();
            currentProduct.setName(product.getName());
            currentProduct.setDescription(product.getDescription());
            product.setPriceForMeasurementUnit(product.getPriceForMeasurementUnit());
            if (file != null) {
                imgService.deleteImage(currentProduct.getImageName());
                currentProduct.setImageName(imgService.saveImage(file));
            }
            productRepo.save(currentProduct);
        } else throw new NotFoundException("Cannot update product with id " + product.getId() + ". Product not exist");
    }

    public Page<Product> getProducts(int page, int size, Long subcategoryId, Double minPrice, Double maxPrice, String sortType) throws NotFoundException {
        if (page > -1 && size > -1) {
            if (subcategoryRepo.existsById(subcategoryId)) {
                Specification<Product> endSpecification = productsByCategoryId(subcategoryId);

                if (minPrice != null) {
                    endSpecification = endSpecification.and(productsByMinPrice(minPrice));
                }
                if (maxPrice != null) {
                    endSpecification = endSpecification.and(productsByMaxPrice(maxPrice));
                }

                Sort sort;
                if (sortType != null) {
                    switch (sortType.toUpperCase()) {
                        case "PRICE_ASC":
                            sort = Sort.by("priceForMeasurementUnit").ascending();
                            break;
                        case "PRICE_DESC":
                            sort = Sort.by("priceForMeasurementUnit").descending();
                            break;
                        case "POPULAR":
                            sort = Sort.by("viewsCount").descending();
                            break;
                        default:
                            sort = Sort.by("viewsCount").descending();
                            break;
                    }
                } else {
                    sort = Sort.by("viewsCount").descending();
                }

                return productRepo.findAll(endSpecification, PageRequest.of(page, size, sort));
            } else throw new NotFoundException("Unable to find subcategory with id " + subcategoryId);
        } else throw new IllegalArgumentException("Page number and size must be greater than 0");
    }

    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isPresent()) {
            return opProduct.get();
        } else throw new NotFoundException("Cannot find product with id " + id);
    }

    public Page<Product> getSellerProducts(int page, int size) {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        return productRepo.findBySellerUserId(user.getId(), PageRequest.of(page, size));
    }

    public void deleteProduct(Long productId) throws NotFoundException {
        User user = (User) userService.loadUserByUsername(getPrincipal().getName());
        Optional<Product> opProduct = productRepo.findBySellerUserIdAndId(user.getId(), productId);
        if (opProduct.isPresent()) {
            if (!orderedItemRepo.existsByProductId(productId)) {
                cartItemRepo.deleteByProductId(productId);
                productRepo.deleteById(productId);
            } else throw new IllegalArgumentException("Cannot delete product, because it is already ordered");
        } else throw new NotFoundException("Product not found");
    }

    public boolean isProductExist(String name) {
        return productRepo.existsByName(name);
    }


    private Principal getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}

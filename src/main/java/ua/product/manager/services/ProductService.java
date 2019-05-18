package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.*;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.*;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import static ua.product.manager.Specifications.ProductSpecification.*;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final ImgService imgService;
    private final UserService userService;
    private final SubcategoryRepo subcategoryRepo;
    private final MeasurementUnitRepo measurementUnitRepo;
    private final SellerRepo sellerRepo;
    private final OrderedItemRepo orderedItemRepo;
    private final CartItemRepo cartItemRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, ImgService imgService, UserService userService, SubcategoryRepo subcategoryRepo, MeasurementUnitRepo measurementUnitRepo, SellerRepo sellerRepo, OrderedItemRepo orderedItemRepo, CartItemRepo cartItemRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.imgService = imgService;
        this.userService = userService;
        this.subcategoryRepo = subcategoryRepo;
        this.measurementUnitRepo = measurementUnitRepo;
        this.sellerRepo = sellerRepo;
        this.orderedItemRepo = orderedItemRepo;
        this.cartItemRepo = cartItemRepo;
        this.categoryRepo = categoryRepo;
    }

    public void addProduct(MultipartFile file, Product product, Principal principal) throws IOException, ObjectExistException {
        boolean isMeasurementUnitExist = measurementUnitRepo.existsById(product.getMeasurementUnit().getId());
        if (isMeasurementUnitExist) {
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
                            sort = Sort.by("ordersCount").descending();
                            break;
                        default:
                            sort = Sort.by("ordersCount").descending();
                    }
                } else {
                    sort = Sort.by("ordersCount").descending();
                }

                return productRepo.findAll(endSpecification, PageRequest.of(page, size, sort));
            } else throw new NotFoundException("Unable to find subcategory with id " + subcategoryId);
        } else throw new IllegalArgumentException("Page number and size must be greater than 0");
    }

    public Iterable<Product> getPopularProducts() {
        return productRepo.findAll(PageRequest.of(0, 24, Sort.by("ordersCount").descending()));
    }

    public List<Product> getCategoryPopular(Long categoryId) throws NotFoundException {
        Optional<Category> opCategory = categoryRepo.findById(categoryId);
        if (opCategory.isPresent()) {
            return productRepo.findAllBySubcategoryCategory(opCategory.get(), PageRequest.of(0, 16, Sort.by("ordersCount").descending()));
        } else throw new NotFoundException("Category not found");
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

package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.MeasurementUnit;
import ua.product.manager.entities.Product;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.services.MeasurementUnitService;
import ua.product.manager.services.ProductService;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;
    private MeasurementUnitService measurementUnitService;

    @Autowired
    public ProductController(ProductService productService, MeasurementUnitService measurementUnitService) {
        this.productService = productService;
        this.measurementUnitService = measurementUnitService;
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(name = "p") int page,
            @RequestParam(name = "s") int size,
            @RequestParam(name = "sc") Long subcategoryId,
            @RequestParam(name = "minPrice", required = false) Double minPrice,
            @RequestParam(name = "maxPrice", required =  false) Double maxPrice
    ) throws NotFoundException {
        return productService.getProducts(page, size,subcategoryId, minPrice, maxPrice);
    }

    @PostMapping
    public void addProduct(
            @RequestPart(name = "image")MultipartFile file,
            @Valid @RequestPart(name = "product")Product product,
            Principal principal) throws IOException, ObjectExistException {

        productService.addProduct(file, product, principal);
    }

    @PostMapping("/measurement-unit")
    public void addMeasurementUnit(@Valid @RequestBody MeasurementUnit unit) {
        measurementUnitService.saveMeasurementUnit(unit);
    }

    @GetMapping("/measurement-unit")
    public Iterable<MeasurementUnit> getAllMeasurementUnits() {
        return measurementUnitService.getAllMeasurementUnits();
    }

    @PostMapping("/unit-short-name-check")
    public Map<String, Boolean> checkUnitShortName(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", measurementUnitService.isShortNameExist(payload.get("shortName")));
    }

    @PostMapping("/unit-full-name-check")
    public Map<String, Boolean> checkUnitFullName(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", measurementUnitService.isFullNameExist(payload.get("fullName")));
    }

    @PostMapping("/check")
    public Map<String, Boolean> checkProductName(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", productService.isProductExist(payload.get("productName")));
    }
}

package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.MeasurementUnit;
import ua.product.manager.services.MeasurementUnitService;
import ua.product.manager.services.ProductService;

import javax.validation.Valid;

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

    @PostMapping("/measurement-unit")
    public void addMeasurementUnit(@Valid @RequestBody MeasurementUnit unit) {
        measurementUnitService.saveMeasurementUnit(unit);
    }

    @GetMapping("/measurement-unit")
    public Iterable<MeasurementUnit> getAllMeasurementUnits() {
        return measurementUnitService.getAllMeasurementUnits();
    }
}

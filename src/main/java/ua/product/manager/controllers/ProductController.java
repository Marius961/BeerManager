package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.MeasurementUnit;
import ua.product.manager.services.MeasurementUnitService;
import ua.product.manager.services.ProductService;

import javax.validation.Valid;
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
}

package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.Subcategory;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.services.SubcategoryService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoriesController {

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoriesController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping
    public void addSubcategory(@Valid @RequestBody Subcategory subcategory) throws NotFoundException {
        subcategoryService.saveSubcategory(subcategory);
    }

    @DeleteMapping("/{id}")
    public void deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
    }

    @PutMapping
    public void updateSubcategory(@Valid @RequestBody Subcategory subcategory) throws NotFoundException {
        subcategoryService.updateSubcategory(subcategory);
    }

    @GetMapping("/{id}")
    public Subcategory getSubcategoriesByCategoryId(@PathVariable Long id) throws NotFoundException {
        return subcategoryService.getSubcategoryById(id);
    }

    @PostMapping("/check")
    public Map<String, Boolean> checkUnitFullName(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", subcategoryService.isSubcategoryExist(payload.get("subcategoryName")));
    }
}

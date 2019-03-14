package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.product.manager.entities.Category;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.services.CategoriesService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping
    public void addCategory(@Valid @RequestBody Category category) throws ObjectExistException {
        categoriesService.saveCategory(category);
    }

    @GetMapping
    public Iterable<Category> getCategories() {
        return categoriesService.getAllCategories();
    }
}

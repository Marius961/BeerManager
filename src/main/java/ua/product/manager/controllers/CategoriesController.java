package ua.product.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.Category;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.services.CategoriesService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping
    public void addCategory(@Valid @RequestPart(name = "category") Category category, @RequestPart(name = "image") MultipartFile file) throws ObjectExistException, IOException {
        categoriesService.saveCategory(category, file);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) throws NotFoundException {
        return categoriesService.getCategory(id);
    }

    @PutMapping
    public void updateCategory(@Valid @RequestPart(name = "category") Category category, @RequestPart(name = "image", required = false) MultipartFile file) throws IOException, NotFoundException {
        categoriesService.updateCategory(category, file);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) throws NotFoundException {
        categoriesService.deleteCategory(id);
    }

    @GetMapping
    public Iterable<Category> getCategories() {
        return categoriesService.getAllCategories();
    }

    @PostMapping("/check")
    public Map<String, Boolean> checkUnitFullName(@RequestBody Map<String, String> payload) {
        return Collections.singletonMap("isExist", categoriesService.isCategoryExist(payload.get("categoryName")));
    }
}

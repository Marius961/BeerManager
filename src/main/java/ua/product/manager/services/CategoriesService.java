package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.Category;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.CategoryRepo;

import java.io.IOException;

@Service
public class CategoriesService {

    private CategoryRepo categoryRepo;
    private ImgService imgService;

    @Autowired
    public CategoriesService(CategoryRepo categoryRepo, ImgService imgService) {
        this.categoryRepo = categoryRepo;
        this.imgService = imgService;
    }



    public void saveCategory(Category category, MultipartFile file) throws ObjectExistException, IOException {
        if (!categoryRepo.existsByName(category.getName())) {
            category.setImageName(imgService.saveImage(file));
            categoryRepo.save(category);
        } else throw new ObjectExistException("Category with name " + category.getName() + " already exist");
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public boolean isCategoryExist(String categoryName) {
        return categoryRepo.existsByName(categoryName);
    }
}

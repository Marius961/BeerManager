package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.product.manager.entities.Category;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.CategoryRepo;

import java.io.IOException;
import java.util.Optional;

@Service
public class CategoriesService {

    private final CategoryRepo categoryRepo;
    private final ImgService imgService;

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

    public void updateCategory(Category category, MultipartFile file) throws IOException, NotFoundException {
        Optional<Category> opCategory = categoryRepo.findById(category.getId());
        if (opCategory.isPresent()) {
            if (file != null) {
                imgService.deleteImage(opCategory.get().getImageName());
                category.setImageName(imgService.saveImage(file));
            } else if (category.getImageName().equals("") || category.getImageName() == null) {
                category.setImageName(opCategory.get().getImageName());
            }
            if (categoryRepo.countByCategoryNameAndNotId(category.getName(), category.getId()) == 0) {
                categoryRepo.save(category);
            } else throw new IllegalArgumentException("Category with name \"" + category.getName() + "\" already exist");
        } else throw new NotFoundException("Cannot find category with id " + category.getId());
    }

    public void deleteCategory(Long id) throws NotFoundException {
        Optional<Category> opCategory = categoryRepo.findById(id);
        if (opCategory.isPresent()) {
            if (opCategory.get().getSubcategories().isEmpty()) {
                categoryRepo.deleteById(id);
            } else throw new IllegalArgumentException("Cannot delete category, first delete all subcategories");
        } else throw new NotFoundException("Cannot find category with id " + id);
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategory(Long id) throws NotFoundException {
        Optional<Category> opCategory = categoryRepo.findById(id);
        if (opCategory.isPresent()) {
            return opCategory.get();
        } else throw new NotFoundException("Cannot find category with id " + id);
    }

    public boolean isCategoryExist(String categoryName) {
        return categoryRepo.existsByName(categoryName);
    }
}

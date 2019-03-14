package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.Category;
import ua.product.manager.exceptions.ObjectExistException;
import ua.product.manager.repo.CategoryRepo;

@Service
public class CategoriesService {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoriesService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    public void saveCategory(Category category) throws ObjectExistException {
        if (!categoryRepo.existsByName(category.getName())) {
            categoryRepo.save(category);
        } else throw new ObjectExistException("Category with name " + category.getName() + " already exist");
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}

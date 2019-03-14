package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.Category;
import ua.product.manager.entities.Subcategory;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.CategoryRepo;
import ua.product.manager.repo.SubcategoryRepo;

import java.util.Optional;

@Service
public class SubcategoryService {

    private SubcategoryRepo subcategoryRepo;
    private CategoryRepo categoryRepo;


    @Autowired
    public SubcategoryService(SubcategoryRepo subcategoryRepo, CategoryRepo categoryRepo) {
        this.subcategoryRepo = subcategoryRepo;
        this.categoryRepo = categoryRepo;
    }

    public void saveSubcategory(Subcategory subcategory) throws NotFoundException {

        if (categoryRepo.existsById(subcategory.getCategory().getId())) {
            subcategoryRepo.save(subcategory);
        } else throw new NotFoundException("Cannot found category with id " + subcategory.getCategory().getId());
    }

    public Iterable<Subcategory> getSubcategoriesByCategoryId(Long categoryId) throws NotFoundException {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isPresent()) {
            return subcategoryRepo.findAllByCategory(category.get());
        } else throw new NotFoundException("Cannot find subcategories, because category with id " + categoryId + " not exist");
    }
}

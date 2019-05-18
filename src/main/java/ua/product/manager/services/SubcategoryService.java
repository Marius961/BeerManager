package ua.product.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.product.manager.entities.Category;
import ua.product.manager.entities.Subcategory;
import ua.product.manager.exceptions.NotFoundException;
import ua.product.manager.repo.CategoryRepo;
import ua.product.manager.repo.ProductRepo;
import ua.product.manager.repo.SubcategoryRepo;

import java.util.Optional;

@Service
public class SubcategoryService {

    private final SubcategoryRepo subcategoryRepo;
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @Autowired
    public SubcategoryService(SubcategoryRepo subcategoryRepo, CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.subcategoryRepo = subcategoryRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    public void saveSubcategory(Subcategory subcategory) throws NotFoundException {
        boolean isCategoryExist = categoryRepo.existsById(subcategory.getCategory().getId());
        boolean isNameUnique = !categoryRepo.existsByName(subcategory.getName());
        if (isCategoryExist && isNameUnique) {
            subcategoryRepo.save(subcategory);
        } else throw new NotFoundException("Cannot found category with id " + subcategory.getCategory().getId());
    }

    public Subcategory getSubcategoryById(Long id) throws NotFoundException {
        Optional<Subcategory> opSubcategory = subcategoryRepo.findById(id);
        if (opSubcategory.isPresent()) {
            return opSubcategory.get();
        } else throw new NotFoundException("Cannot find subcategory with id " + id);
    }

    public void deleteSubcategory(Long id) {
        if (!productRepo.existsBySubcategoryId(id)) {
            subcategoryRepo.deleteById(id);
        } else throw new IllegalArgumentException("Cannot delete not empty subcategory");
    }

    public void updateSubcategory(Subcategory subcategory) throws NotFoundException {
        if (subcategoryRepo.existsById(subcategory.getId())) {
            boolean isCategoryExist = categoryRepo.existsById(subcategory.getCategory().getId());
            boolean isNameNotSameAsCategoryName = !categoryRepo.existsByName(subcategory.getName());
            if (isCategoryExist && isNameNotSameAsCategoryName) {
                if(subcategoryRepo.countByNameAndIdNot(subcategory.getName(), subcategory.getId()) == 0) {
                    subcategoryRepo.save(subcategory);
                } else throw new IllegalArgumentException("Subcategory with name \"" + subcategory.getName() + "\" already exist");
            } else throw new NotFoundException("Invalid category id, or category with name " + subcategory.getName() + " exist");
        } else throw new NotFoundException("Cannot find subcategory with id " + subcategory.getId());

    }

    public boolean isSubcategoryExist(String name) {
        return subcategoryRepo.existsByName(name);
    }
}

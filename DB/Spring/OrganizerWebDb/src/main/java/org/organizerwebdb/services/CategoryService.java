package org.organizerwebdb.services;

import org.organizerwebdb.models.Category;
import org.organizerwebdb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository CategoryRepository;

    public List<Category> getAllCategories() {
        return CategoryRepository.findAll();
    }

    Optional<Category> getOneCategory(String id) {
        return CategoryRepository.findById(id);
    }

    public Optional<Category> createCategory(Category category) {
        if (!category.getCategoryName().isBlank()) {
            Category category_saved = CategoryRepository.save(category);
            return Optional.of(category_saved);
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Category> modifyCategory(Category category) {
        Optional<Category> o_category_to_modify = CategoryRepository.findById(category.getCategoryName());
        if (o_category_to_modify.isPresent()) {
            Category category_to_modify = o_category_to_modify.get();
            category_to_modify.setCategoryName(category.getCategoryName());
            Category category_saved = CategoryRepository.save(category_to_modify);
            return Optional.of(category_saved);
        }
        else {
            return Optional.empty();
        }
    }

    public boolean deleteCategory(String category_name) {
        return CategoryRepository.deleteCategoryByCategoryName(category_name) == 1;
    }

}

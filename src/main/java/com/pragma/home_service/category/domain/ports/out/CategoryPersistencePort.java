package com.pragma.home_service.category.domain.ports.out;

import com.pragma.home_service.category.domain.model.CategoryModel;

import java.util.List;

public interface CategoryPersistencePort {
    void saveCategory(CategoryModel category);

    List<CategoryModel> getCategories();

    CategoryModel getCategoryByName(String name);
}

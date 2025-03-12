package com.pragma.home_service.category.domain.ports.in;

import com.pragma.home_service.category.domain.model.CategoryModel;

import java.util.List;

public interface CategoryServicePort {
    void saveCategory(CategoryModel category);
    List<CategoryModel> GetCategories();
}

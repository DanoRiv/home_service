package com.pragma.home_service.category.domain.ports.out;

import com.pragma.home_service.category.domain.model.CategoryModel;

import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;

public interface CategoryPersistencePort {
    void saveCategory(CategoryModel category);

    PaginatedResult<CategoryModel> getCategories(int page, int size, String sort);

    CategoryModel getCategoryByName(String name);
}

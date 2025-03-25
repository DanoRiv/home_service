package com.pragma.home_service.category.domain.ports.in;

import com.pragma.home_service.category.domain.model.CategoryModel;

import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;

public interface CategoryServicePort {
    void saveCategory(CategoryModel category);
    PaginatedResult<CategoryModel> getCategories(int page, int size, String sort);
}

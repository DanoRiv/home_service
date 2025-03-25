package com.pragma.home_service.category.application.services;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.application.dto.response.SaveCategoryResponse;

import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;

public interface CategoryService {
    SaveCategoryResponse saveCategory(SaveCategoryRequest request);
    PaginatedResult<CategoryResponse> getCategories(int page, int size, String sort);
}

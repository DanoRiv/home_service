package com.pragma.home_service.category.application.services;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.application.dto.response.SaveCategoryResponse;

import java.util.List;

public interface CategoryService {
    SaveCategoryResponse saveCategory(SaveCategoryRequest request);
    List<CategoryResponse> getCategories();
}

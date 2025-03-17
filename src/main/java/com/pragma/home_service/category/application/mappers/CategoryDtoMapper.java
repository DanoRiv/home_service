package com.pragma.home_service.category.application.mappers;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.domain.model.CategoryModel;

import java.util.List;

public interface CategoryDtoMapper {
    CategoryModel toModel(SaveCategoryRequest request);
    List<CategoryResponse> toResponse(List<CategoryModel> categories);
}

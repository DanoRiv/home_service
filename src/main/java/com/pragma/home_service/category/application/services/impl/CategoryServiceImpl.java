package com.pragma.home_service.category.application.services.impl;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.application.dto.response.SaveCategoryResponse;
import com.pragma.home_service.category.application.mappers.CategoryDtoMapper;
import com.pragma.home_service.category.application.services.CategoryService;
import com.pragma.home_service.category.domain.ports.in.CategoryServicePort;

import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;

import com.pragma.home_service.commons.config.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;
    @Override
    public SaveCategoryResponse saveCategory(SaveCategoryRequest request) {
        categoryServicePort.saveCategory(categoryDtoMapper.toModel(request));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_SUCCESS_MESSAGE, LocalDateTime.now());
    }

    @Override
    public PaginatedResult<CategoryResponse> getCategories(int page, int size, String sort) {
       return categoryDtoMapper.toResponsePaginatedList(categoryServicePort.getCategories(page, size, sort));
    }
}

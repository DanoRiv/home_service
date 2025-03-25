package com.pragma.home_service.category.application.mappers;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.domain.model.CategoryModel;

import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {
    CategoryModel toModel(SaveCategoryRequest request);

    PaginatedResult<CategoryResponse> toResponsePaginatedList(PaginatedResult<CategoryModel> categories);
}

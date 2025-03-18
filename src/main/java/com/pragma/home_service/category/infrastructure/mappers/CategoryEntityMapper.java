package com.pragma.home_service.category.infrastructure.mappers;

import com.pragma.home_service.category.domain.model.CategoryModel;
import com.pragma.home_service.category.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(CategoryModel categoryModel);
    CategoryModel toModel(CategoryEntity categoryEntity);
    List<CategoryModel> toModelList(List<CategoryEntity> categoryEntities);
}

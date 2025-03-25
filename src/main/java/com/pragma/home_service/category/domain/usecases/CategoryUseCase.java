package com.pragma.home_service.category.domain.usecases;

import com.pragma.home_service.category.domain.exception.DuplicatedEntryException;
import com.pragma.home_service.category.domain.exception.EmptyFieldException;
import com.pragma.home_service.category.domain.model.CategoryModel;
import com.pragma.home_service.category.domain.ports.in.CategoryServicePort;
import com.pragma.home_service.category.domain.ports.out.CategoryPersistencePort;
import com.pragma.home_service.category.domain.utils.constants.DomainConstants;

import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;

public class CategoryUseCase implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(CategoryModel category) {
        if(category.getName().isBlank()) {
            throw new EmptyFieldException(DomainConstants.FIELD_NAME_NULL_MESSAGE);
        }
        if(category.getDescription().isBlank()) {
            throw new EmptyFieldException(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        }
        CategoryModel existingCategory = categoryPersistencePort.getCategoryByName(category.getName());
        if(existingCategory != null) {
            throw new DuplicatedEntryException(DomainConstants.DUPLICATED_CATEGORY_EXCEPTION_MESSAGE);
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public PaginatedResult<CategoryModel> getCategories(int page, int size, String sort) {
        return categoryPersistencePort.getCategories(page, size, sort);
    }
}

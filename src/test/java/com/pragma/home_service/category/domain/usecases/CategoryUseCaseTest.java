package com.pragma.home_service.category.domain.usecases;

import com.pragma.home_service.category.domain.exception.DuplicatedEntryException;
import com.pragma.home_service.category.domain.exception.EmptyFieldException;
import com.pragma.home_service.category.domain.model.CategoryModel;
import com.pragma.home_service.category.domain.ports.out.CategoryPersistencePort;
import com.pragma.home_service.category.domain.utils.constants.DomainConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    void shouldCreateCategorySuccessfully() {

        CategoryModel category = new CategoryModel(null, "Apartment", "Apartments Category");
        doNothing().when(categoryPersistencePort).saveCategory(any(CategoryModel.class));

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {

        CategoryModel category = new CategoryModel(null, "", "Some description");
        Exception exception = assertThrows(EmptyFieldException.class, () -> categoryUseCase.saveCategory(category));

        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {

        CategoryModel category = new CategoryModel(null, "TestName", "");
        Exception exception = assertThrows(EmptyFieldException.class, () -> categoryUseCase.saveCategory(category));

        assertEquals(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsDuplicated(){
        // Arrange
        CategoryModel category = new CategoryModel(null, "Apartment", "Apartments Category");
        when(categoryPersistencePort.getCategoryByName("Apartment")).thenReturn(category);

        // Act
        Exception exception = assertThrows(DuplicatedEntryException.class, () -> categoryUseCase.saveCategory(category));

        // Assert
        assertEquals(DomainConstants.DUPLICATED_CATEGORY_EXCEPTION_MESSAGE, exception.getMessage());
    }

}

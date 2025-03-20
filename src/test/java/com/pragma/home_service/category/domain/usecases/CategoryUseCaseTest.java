package com.pragma.home_service.category.domain.usecases;

import com.pragma.home_service.category.domain.exception.DuplicatedEntryException;
import com.pragma.home_service.category.domain.exception.EmptyFieldException;
import com.pragma.home_service.category.domain.exception.NoDataFoundException;
import com.pragma.home_service.category.domain.model.CategoryModel;
import com.pragma.home_service.category.domain.ports.out.CategoryPersistencePort;
import com.pragma.home_service.category.domain.utils.constants.DomainConstants;
import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    void shouldReturnPaginatedResult(){
        //arrange
        CategoryModel category1 = new CategoryModel(1L, "Books", "Books category");
        CategoryModel category2 = new CategoryModel(2L, "Electronics", "Electronics category");
        List<CategoryModel> content = Arrays.asList(category1, category2);

        long totalElements = 2;
        int totalPages = 1;
        int size = 2;
        int page = 0;

        PaginatedResult<CategoryModel> expectedResult = new PaginatedResult<>(content, page, size, totalElements, totalPages);

        when(categoryPersistencePort.getCategories(0, 2, "asc")).thenReturn(expectedResult);

        //act
        PaginatedResult<CategoryModel> actualResult = categoryUseCase.getCategories(0, 2, "asc");
        //assert
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
        assertEquals(content, actualResult.content());
        assertEquals(totalElements, actualResult.totalElements());
        assertEquals(totalPages, actualResult.totalPages());
        assertEquals(page, actualResult.page());
        assertEquals(size, actualResult.size());

        verify(categoryPersistencePort, times(1)).getCategories(0, 2, "asc");
    }
    @Test
    void shouldThrowExceptionWhenNoDataFound(){
        //arrange
        when(categoryPersistencePort.getCategories(0, 2, "asc")).thenThrow(new NoDataFoundException());

        //act & assert
        assertThrows(NoDataFoundException.class, () -> categoryUseCase.getCategories(0, 2, "asc"));
    }

}

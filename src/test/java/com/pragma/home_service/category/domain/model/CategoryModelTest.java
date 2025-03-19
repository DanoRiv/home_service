package com.pragma.home_service.category.domain.model;

import com.pragma.home_service.category.domain.exception.DescriptionLengthExceededException;
import com.pragma.home_service.category.domain.exception.NameLengthExceededException;
import com.pragma.home_service.category.domain.utils.constants.DomainConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryModelTest {
    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        NullPointerException exception = assertThrows(NullPointerException.class, () ->  new CategoryModel(1L, null, "Some description"));
        assertEquals(DomainConstants.FIELD_NAME_NULL_MESSAGE, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenDescriptionIsNull(){
        NullPointerException exception = assertThrows(NullPointerException.class, () ->  new CategoryModel(1L, "Apartment", null));
        assertEquals(DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenNameExceedsMaxLength() {
        assertThrows(NameLengthExceededException.class, () -> new CategoryModel(1L, "Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Some description"));
    }
    @Test
    void shouldThrowExceptionWhenDescriptionExceedsMaxLength() {
        assertThrows(DescriptionLengthExceededException.class, () -> new CategoryModel(1L, "Apartment", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
    }
}
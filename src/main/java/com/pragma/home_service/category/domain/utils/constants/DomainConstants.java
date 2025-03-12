package com.pragma.home_service.category.domain.utils.constants;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null or empty";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null or empty";
    public static final String DUPLICATED_CATEGORY_EXCEPTION_MESSAGE = "The category already exists";

    public static final Integer MAX_NAME_LENGTH = 50;
    public static final Integer MAX_DESCRIPTION_LENGTH = 90;
}

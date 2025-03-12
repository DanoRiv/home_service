package com.pragma.home_service.category.domain.model;

import com.pragma.home_service.category.domain.exception.DescriptionLengthExceededException;
import com.pragma.home_service.category.domain.exception.NameLengthExceededException;

import static com.pragma.home_service.category.domain.utils.constants.DomainConstants.*;
import static java.util.Objects.requireNonNull;
public class CategoryModel {
    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        this.id = id;
        this.name = validateName(name);
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = validateDescription(description);
    }

    private String validateName(String name){
        if(name.length() > MAX_NAME_LENGTH) throw new NameLengthExceededException();
        return requireNonNull(name, FIELD_NAME_NULL_MESSAGE);
    }

    private String validateDescription(String description){
        if(description.length() > MAX_DESCRIPTION_LENGTH) throw new DescriptionLengthExceededException();
        return requireNonNull(description, FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}

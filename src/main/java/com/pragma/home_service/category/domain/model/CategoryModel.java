package com.pragma.home_service.category.domain.model;

import com.pragma.home_service.category.domain.exception.DescriptionLengthExceededException;
import com.pragma.home_service.category.domain.exception.NameLengthExceededException;

import static com.pragma.home_service.category.domain.utils.constants.DomainConstants.*;

public class CategoryModel {
    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
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
        if(name == null) throw new NullPointerException(FIELD_NAME_NULL_MESSAGE);
        if(name.length() > MAX_NAME_LENGTH) throw new NameLengthExceededException();
        return name;
    }

    private String validateDescription(String description){
        if(description == null) throw new NullPointerException(FIELD_DESCRIPTION_NULL_MESSAGE);
        if(description.length() > MAX_DESCRIPTION_LENGTH) throw new DescriptionLengthExceededException();
        return description;
    }
}

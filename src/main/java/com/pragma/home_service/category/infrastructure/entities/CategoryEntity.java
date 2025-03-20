package com.pragma.home_service.category.infrastructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.pragma.home_service.category.domain.utils.constants.DomainConstants.*;
import static com.pragma.home_service.category.infrastructure.exceptionhandler.ExceptionConstants.*;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotBlank(message = FIELD_NAME_NULL_MESSAGE)
    @Size(max = MAX_NAME_LENGTH, message = NAME_LENGTH_EXCEEDED)
    private String name;
    @Column(nullable = false)
    @NotBlank(message = FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(max = MAX_DESCRIPTION_LENGTH, message = DESCRIPTION_LENGTH_EXCEEDED)
    private String description;
}

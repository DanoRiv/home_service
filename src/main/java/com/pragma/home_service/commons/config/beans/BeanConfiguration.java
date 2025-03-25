package com.pragma.home_service.commons.config.beans;

import com.pragma.home_service.category.domain.ports.in.CategoryServicePort;
import com.pragma.home_service.category.domain.ports.out.CategoryPersistencePort;
import com.pragma.home_service.category.domain.usecases.CategoryUseCase;
import com.pragma.home_service.category.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import com.pragma.home_service.category.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.home_service.category.infrastructure.repositories.mysql.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public CategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public CategoryPersistencePort categoryPersistencePort(){
        return new CategoryPersistenceAdapter(categoryRepository, categoryEntityMapper);
    }
}

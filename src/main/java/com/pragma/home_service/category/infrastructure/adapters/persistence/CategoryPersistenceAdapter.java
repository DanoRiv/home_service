package com.pragma.home_service.category.infrastructure.adapters.persistence;

import com.pragma.home_service.category.domain.model.CategoryModel;
import com.pragma.home_service.category.domain.ports.out.CategoryPersistencePort;
import com.pragma.home_service.category.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.home_service.category.infrastructure.repositories.mysql.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    @Override
    public void saveCategory(CategoryModel category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<CategoryModel> GetCategories() {
        return categoryEntityMapper.toModelList(categoryRepository.findAll());
    }

    @Override
    public CategoryModel getCategoryByName(String name) {
        return categoryEntityMapper.toModel(categoryRepository.findByName(name).orElse(null));
    }
}

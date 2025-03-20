package com.pragma.home_service.category.infrastructure.adapters.persistence;

import com.pragma.home_service.category.domain.exception.NoDataFoundException;
import com.pragma.home_service.category.domain.model.CategoryModel;
import com.pragma.home_service.category.domain.ports.out.CategoryPersistencePort;
import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;
import com.pragma.home_service.category.infrastructure.entities.CategoryEntity;
import com.pragma.home_service.category.infrastructure.mappers.CategoryEntityMapper;
import com.pragma.home_service.category.infrastructure.repositories.mysql.CategoryRepository;
import com.pragma.home_service.commons.config.utils.Sorting;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PaginatedResult<CategoryModel> getCategories(int page, int size, String sort) {
        Sorting sorting = new Sorting();
        Pageable pageable = PageRequest.of(page, size, sorting.sortBy("name", sort));
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryModel> categoriesContent = categoryEntityMapper.toModelList(categoryPage.getContent());
        if(categoriesContent.isEmpty()){
            throw new NoDataFoundException();
        }
        return new PaginatedResult<>(
                categoriesContent,
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages()
        );
    }

    @Override
    public CategoryModel getCategoryByName(String name) {
        return categoryEntityMapper.toModel(categoryRepository.findByName(name).orElse(null));
    }
}

package com.pragma.home_service.category.infrastructure.endpoints.rest;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.application.dto.response.SaveCategoryResponse;
import com.pragma.home_service.category.application.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<SaveCategoryResponse> saveCategory(@RequestBody SaveCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCateries(){
        return ResponseEntity.ok(categoryService.getCategories());
    }
}

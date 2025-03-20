package com.pragma.home_service.category.infrastructure.endpoints.rest;

import com.pragma.home_service.category.application.dto.request.SaveCategoryRequest;
import com.pragma.home_service.category.application.dto.response.CategoryResponse;
import com.pragma.home_service.category.application.dto.response.SaveCategoryResponse;
import com.pragma.home_service.category.application.services.CategoryService;
import com.pragma.home_service.category.domain.utils.pagination.PaginatedResult;
import com.pragma.home_service.category.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Create new category")
    @ApiResponse(responseCode = "201", description = "Category created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaveCategoryRequest.class)))
    @ApiResponse(responseCode = "400", description = "Couldn't create category", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @PostMapping("/")
    public ResponseEntity<SaveCategoryResponse> saveCategory(@RequestBody SaveCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(request));
    }

    @Operation(summary = "Get a paginated list of categories")
    @ApiResponse(responseCode = "201", description = "Categories retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaginatedResult.class)))
    @ApiResponse(responseCode = "404", description = "No data found for categories", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    @GetMapping("/")
    public ResponseEntity<PaginatedResult<CategoryResponse>> getAllCateries(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(categoryService.getCategories(page, size, sort));
    }
}

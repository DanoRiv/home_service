package com.pragma.home_service.category.domain.utils.pagination;

import java.util.List;

public record PaginatedResult<T>(List<T> content, int page, int size, long totalElements, int totalPages) {
}
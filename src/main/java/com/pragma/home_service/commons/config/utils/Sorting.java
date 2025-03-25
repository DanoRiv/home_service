package com.pragma.home_service.commons.config.utils;

import org.springframework.data.domain.Sort;

public class Sorting {

    public Sort sortBy(String field, String direction){
        if(direction == null || direction.isBlank()){
            return Sort.unsorted();
        }
        return switch (direction.toLowerCase()) {
            case "asc" -> Sort.by(field).ascending();
            case "desc" -> Sort.by(field).descending();
            default -> Sort.unsorted();
        };
    }
}
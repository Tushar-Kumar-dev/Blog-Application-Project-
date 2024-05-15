package com.BlogApplication.BlogApplicationProject.services;

import com.BlogApplication.BlogApplicationProject.payloads.CategoryDto;

import java.util.List;

public interface CategoryServices {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    void deleteCategory(Long categoryId);
    CategoryDto getCategoryById(Long categoryId);
    List<CategoryDto> getCategories();
}

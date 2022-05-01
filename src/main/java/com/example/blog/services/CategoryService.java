package com.example.blog.services;

import com.example.blog.payloads.CategoryDto;

import java.util.List;

/**
 * @project: Blog
 * @author: Sarvar55
 */
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getCategories();
}

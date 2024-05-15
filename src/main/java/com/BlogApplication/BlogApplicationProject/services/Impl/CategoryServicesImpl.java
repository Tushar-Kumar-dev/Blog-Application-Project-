package com.BlogApplication.BlogApplicationProject.services.Impl;

import com.BlogApplication.BlogApplicationProject.entity.Category;
import com.BlogApplication.BlogApplicationProject.exception.ResourceNotFoundException;
import com.BlogApplication.BlogApplicationProject.payloads.CategoryDto;
import com.BlogApplication.BlogApplicationProject.repositories.CategoryRepo;
import com.BlogApplication.BlogApplicationProject.services.CategoryServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory( CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id", categoryId));

        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());

        Category updateCategory = categoryRepo.save(category);
        return modelMapper.map(updateCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Id", categoryId));

        categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id", categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map(e->modelMapper.map(e,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }


}

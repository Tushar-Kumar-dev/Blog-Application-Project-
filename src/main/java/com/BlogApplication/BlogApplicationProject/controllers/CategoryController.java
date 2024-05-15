package com.BlogApplication.BlogApplicationProject.controllers;

import com.BlogApplication.BlogApplicationProject.payloads.ApiResponse;
import com.BlogApplication.BlogApplicationProject.payloads.CategoryDto;
import com.BlogApplication.BlogApplicationProject.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryServices categoryServices;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createUser( @Valid @RequestBody CategoryDto categoryDto){

        CategoryDto categoryDto1 = categoryServices.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateUser(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long catId){

        CategoryDto updateCatId = categoryServices.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(updateCatId, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long catId){
        categoryServices.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long catId){
        CategoryDto categoryDto = categoryServices.getCategoryById(catId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategory(){
        List<CategoryDto> categoryDto = categoryServices.getCategories();
        return ResponseEntity.ok(categoryDto);
    }
}
package com.devteria.book.controller;

import com.devteria.book.dto.request.CategoryCreateRequest;
import com.devteria.book.dto.response.ApiResponse;
import com.devteria.book.dto.response.CategoryResponse;
import com.devteria.book.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> create(@RequestBody CategoryCreateRequest request){

        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.create(request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<CategoryResponse>> findAll(){
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.findAll())
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable(value = "id") String id){
        categoryService.delete(id);
        return ApiResponse.<String>builder().result("Category has been deleted").build();
    }
}

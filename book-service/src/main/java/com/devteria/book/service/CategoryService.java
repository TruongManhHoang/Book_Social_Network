package com.devteria.book.service;

import com.devteria.book.dto.request.CategoryCreateRequest;
import com.devteria.book.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    public CategoryResponse create(CategoryCreateRequest request);

    public List<CategoryResponse> findAll();

    public void delete(String id);
}

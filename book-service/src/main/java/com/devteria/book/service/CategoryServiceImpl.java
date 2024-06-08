package com.devteria.book.service;

import com.devteria.book.dto.request.CategoryCreateRequest;
import com.devteria.book.dto.response.CategoryResponse;
import com.devteria.book.entity.Category;
import com.devteria.book.mapper.CategoryMapper;
import com.devteria.book.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {
        Category category = categoryMapper.toCategory(request);

        category = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll() {
        var category = categoryRepository.findAll();
        return category.stream().map(categoryMapper::toCategoryResponse).toList();
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }
}

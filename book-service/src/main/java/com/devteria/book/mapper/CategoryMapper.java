package com.devteria.book.mapper;

import com.devteria.book.dto.request.CategoryCreateRequest;
import com.devteria.book.dto.response.CategoryResponse;
import com.devteria.book.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public Category toCategory(CategoryCreateRequest request);

    public CategoryResponse toCategoryResponse(Category category);
}

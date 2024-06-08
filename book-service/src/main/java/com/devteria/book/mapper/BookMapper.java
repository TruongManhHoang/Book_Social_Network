package com.devteria.book.mapper;

import com.devteria.book.dto.request.BookCreateRequest;
import com.devteria.book.dto.request.BookUpdateRequest;
import com.devteria.book.dto.response.BookResponse;
import com.devteria.book.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "categories", ignore = true)
    public Book toBook(BookCreateRequest request);

    public BookResponse toBookRespose(Book book);

    @Mapping(target = "categories", ignore = true)
    void updateBook(@MappingTarget Book book, BookUpdateRequest request);
}

package com.devteria.book.service;

import com.devteria.book.dto.request.BookCreateRequest;
import com.devteria.book.dto.request.BookUpdateRequest;
import com.devteria.book.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    public BookResponse create(BookCreateRequest request);

    public BookResponse update(BookUpdateRequest request, String id);

    public List<BookResponse> findAll();

    public BookResponse findById(String id);

    public void delete(String id);

}

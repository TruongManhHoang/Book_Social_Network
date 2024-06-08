package com.devteria.book.controller;

import com.devteria.book.dto.request.BookCreateRequest;
import com.devteria.book.dto.request.BookUpdateRequest;
import com.devteria.book.dto.response.ApiResponse;
import com.devteria.book.dto.response.BookResponse;
import com.devteria.book.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/books")
public class BookController {
    BookService bookService;

    @PostMapping
    public ApiResponse<BookResponse> create(@RequestBody BookCreateRequest request){

        return ApiResponse.<BookResponse>builder()
                .result(bookService.create(request))
                .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<BookResponse> update(@RequestBody BookUpdateRequest request, @PathVariable String id){
        return ApiResponse.<BookResponse>builder()
                .result(bookService.update(request, id))
                .build();
    }
    @GetMapping
    public ApiResponse<List<BookResponse>> getBook(){
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.findAll())
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<BookResponse> getBookById(@PathVariable String id){
        return ApiResponse.<BookResponse>builder()
                .result(bookService.findById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBook(@PathVariable String id){
        bookService.delete(id);
        return ApiResponse.<String>builder()
                .result("Book has been deleted!")
                .build();
    }
}

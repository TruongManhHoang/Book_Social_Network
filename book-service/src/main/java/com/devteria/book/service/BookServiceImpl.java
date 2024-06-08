package com.devteria.book.service;

import com.devteria.book.dto.request.BookCreateRequest;
import com.devteria.book.dto.request.BookUpdateRequest;
import com.devteria.book.dto.response.BookResponse;
import com.devteria.book.exception.AppException;
import com.devteria.book.exception.ErrorCode;
import com.devteria.book.mapper.BookMapper;
import com.devteria.book.entity.Book;
import com.devteria.book.repository.BookRepository;
import com.devteria.book.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService{
    BookRepository bookRepository;

    BookMapper bookMapper;

    CategoryRepository categoryRepository;
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('CREATE_DATA')")
    @Override
    public BookResponse create(BookCreateRequest request) {
        if(bookRepository.existsByTitle(request.getTitle())){
            throw new AppException(ErrorCode.BOOK_EXISTED);
        }
        Book book = bookMapper.toBook(request);

        var categories = categoryRepository.findAllById(request.getCategories());
        book.setCategories(new HashSet<>(categories));
        book = bookRepository.save(book);
        return bookMapper.toBookRespose(book) ;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('UPDATE-DATA')")
    public BookResponse update(BookUpdateRequest request, String id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        bookMapper.updateBook(book, request);

        var categories = categoryRepository.findAllById(request.getCategories());
        book.setCategories(new HashSet<>(categories));
        book = bookRepository.save(book);
        return bookMapper.toBookRespose(book) ;
    }

    @Override
//    @PreAuthorize("hasRole('ADMIN') || hasAuthority("-DATA')")
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toBookRespose).toList();
    }


    @Override
    public BookResponse findById(String id) {
        return bookMapper.toBookRespose(
                bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND)));
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}

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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('UPDATE_DATA')")
    public BookResponse update(BookUpdateRequest request, String id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

//        bookMapper.updateBook(book, request);

        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getDescription() != null) book.setDescription(request.getDescription());
        if (request.getPublishedDate() != null) book.setPublishedDate(request.getPublishedDate());
        if (request.getIsbn() != null) book.setIsbn(request.getIsbn());
        if (request.getLanguage() != null) book.setLanguage(request.getLanguage());
        if (request.getPublisher() != null) book.setPublisher(request.getPublisher());

        var categories = categoryRepository.findAllById(request.getCategories());

        if(request.getCategories() != null) book.setCategories(new HashSet<>(categories));
        book = bookRepository.save(book);
        return bookMapper.toBookRespose(book) ;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('GET_DATA')")
    public List<BookResponse> findAll() {
        return bookRepository.findAll().stream().map(bookMapper::toBookRespose).toList();
    }


    @Override
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('FIND_DATA')")
    public BookResponse findById(String id) {
        return bookMapper.toBookRespose(
                bookRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND)));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}

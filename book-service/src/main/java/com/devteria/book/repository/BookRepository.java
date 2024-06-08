package com.devteria.book.repository;

import com.devteria.book.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    boolean existsByTitle(String title);

    Optional<Book> findByTitle(String title);


}

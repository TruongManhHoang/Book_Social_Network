package com.devteria.book.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "books") // Annotation để chỉ định đây là document của MongoDB
public class Book {
    @Id // Annotation cho khóa chính trong MongoDB
    String id;
    String title;
    String author;
    String description;
    LocalDate publishedDate;
    String isbn;
    String language;
    int pageCount;
    String publisher;
    double averageRating;
    int ratingsCount;

    @DBRef
    private Set<Category> categories;
}

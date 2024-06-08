package com.devteria.book.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String author;
    private String description;
    private LocalDate publishedDate;
    private String isbn;
    private String language;
    private int pageCount;
    private String publisher;
    private double averageRating;
    private int ratingsCount;

    @DBRef
    private Set<Category> categories;
}

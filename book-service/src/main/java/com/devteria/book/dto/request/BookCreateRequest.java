package com.devteria.book.dto.request;

import com.devteria.book.entity.Category;
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
public class BookCreateRequest {
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
    private Set<String> categories;
}

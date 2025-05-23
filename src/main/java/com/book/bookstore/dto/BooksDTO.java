package com.book.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by piyumi_navodani
 */
@Getter
@Setter
public class BooksDTO {
    @NotNull
    private String bookName;
    private String authorName;
}

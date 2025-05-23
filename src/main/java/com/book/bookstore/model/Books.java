package com.book.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author by piyumi_navodani
 */

@Entity
@Table(name = "books")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author_name")
    private String authorName;
}

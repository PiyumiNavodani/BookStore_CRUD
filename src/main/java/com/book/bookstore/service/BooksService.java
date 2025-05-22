package com.book.bookstore.service;

import com.book.bookstore.dto.BooksDTO;
import com.book.bookstore.dto.CommonResponse;
import com.book.bookstore.model.Books;

import java.util.List;

/**
 * @author by piyumi_navodani
 */
public interface BooksService {

    CommonResponse<Books> createBooks(BooksDTO booksDTO);
    CommonResponse<List<Books>> getBooksList();

    CommonResponse<Books> getBookById(Integer id);

    CommonResponse<Books> updateBook(Books books, Integer id);

    CommonResponse<Books> deleteBook(Integer id);
}

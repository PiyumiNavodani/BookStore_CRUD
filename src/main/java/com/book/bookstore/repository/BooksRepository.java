package com.book.bookstore.repository;

import com.book.bookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by piyumi_navodani
 */
@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

}

package com.book.bookstore.controller;

import com.book.bookstore.dto.BooksDTO;
import com.book.bookstore.dto.CommonResponse;
import com.book.bookstore.model.Books;
import com.book.bookstore.service.BooksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by piyumi_navodani
 */

@RestController
@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("api/books")
public class BooksController {
//    private final BooksService booksService;
    @Autowired
    private BooksService booksService;

    @PostMapping("/createBook")
    public ResponseEntity<CommonResponse> createBook(BooksDTO booksDTO){
        log.info("BooksController.createBook() method started");
        CommonResponse commonResponse = booksService.createBooks(booksDTO);
        log.info("BooksController.createBook() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @GetMapping("bookList")
    public ResponseEntity<CommonResponse<List<Books>>> getBookList(){
        log.info("BooksController.getBookList() method started");
        CommonResponse commonResponse = booksService.getBooksList();
        log.info("BooksController.getBookList() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getBookById(@PathVariable Integer id){
        log.info("BooksController.getBookById() method started");
        CommonResponse commonResponse = booksService.getBookById(id);
        log.info("BooksController.getBookById() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateBookData(@RequestBody Books books, @PathVariable Integer id){
        log.info("BooksController.updateBoodData() method started");
        CommonResponse commonResponse = booksService.updateBook(books, id);
        log.info("BooksController.updateBoodData() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<CommonResponse> deleteBookData(@PathVariable Integer id){
        log.info("BooksController.deleteBookData() method started");
        CommonResponse commonResponse = booksService.deleteBook(id);
        log.info("BooksController.deleteBookData() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }
}

package com.book.bookstore.controller;

import com.book.bookstore.dto.BooksDTO;
import com.book.bookstore.dto.CommonResponse;
import com.book.bookstore.model.Books;
import com.book.bookstore.service.BooksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by piyumi_navodani
 */

@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BooksController {
//    private final BooksService booksService;

    @Autowired
    private final BooksService booksService;

//    public BooksController(BooksService booksService){
//        this.booksService = booksService;
//    }

    @Tag(name = "create", description = "Create book record")
    @PostMapping("/createBook")
    public ResponseEntity<CommonResponse> createBook(@RequestBody final BooksDTO booksDTO){
        log.info("BooksController.createBook() method started");
        CommonResponse commonResponse = booksService.createBooks(booksDTO);
        log.info("BooksController.createBook() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @Tag(name = "get", description = "Get list of books")
    @GetMapping("bookList")
    public ResponseEntity<CommonResponse<List<Books>>> getBookList(){
        log.info("BooksController.getBookList() method started");
        CommonResponse commonResponse = booksService.getBooksList();
        log.info("BooksController.getBookList() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @Tag(name = "get", description = "Get one book by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getBookById(@PathVariable final Integer id){
        log.info("BooksController.getBookById() method started");
        CommonResponse commonResponse = booksService.getBookById(id);
        log.info("BooksController.getBookById() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @Tag(name = "put", description = "Update ID related book record")
    @PutMapping("/update/{id}")
    public ResponseEntity<CommonResponse> updateBookData(@RequestBody final Books books, @PathVariable final Integer id){
        log.info("BooksController.updateBoodData() method started");
        CommonResponse commonResponse = booksService.updateBook(books, id);
        log.info("BooksController.updateBoodData() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }

    @Tag(name = "delete", description = "Delete ID related book record")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteBookData(@PathVariable final Integer id){
        log.info("BooksController.deleteBookData() method started");
        CommonResponse commonResponse = booksService.deleteBook(id);
        log.info("BooksController.deleteBookData() method ended");
        return ResponseEntity.status(commonResponse.getStatus()).body(commonResponse);
    }
}

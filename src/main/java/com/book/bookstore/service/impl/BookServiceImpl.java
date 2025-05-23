package com.book.bookstore.service.impl;

import com.book.bookstore.dto.BookListDTO;
import com.book.bookstore.dto.BooksDTO;
import com.book.bookstore.dto.CommonResponse;
import com.book.bookstore.model.Books;
import com.book.bookstore.repository.BooksRepository;
import com.book.bookstore.service.BooksService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author by piyumi_navodani
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BooksService {
    private final BooksRepository booksRepository;
    private final ModelMapper modelMapper;

//    public BookServiceImpl(BooksRepository booksRepository, ModelMapper modelMapper) {
//        this.booksRepository = booksRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public CommonResponse<Books> createBooks(final BooksDTO booksDTO) {
        log.info("BookServiceImpl.createBooks() method started");
        CommonResponse commonResponse =  new CommonResponse<>();
        if (!(booksDTO.getBookName().equals("")) || !(booksDTO.getAuthorName().equals(""))){
            Books books = new Books();
            books.setBookName(booksDTO.getBookName());
            books.setAuthorName(booksDTO.getAuthorName());

            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setMessage("New book created");
            commonResponse.setData(books);

            booksRepository.save(books);
        }else {
            commonResponse.setStatus(HttpStatus.BAD_REQUEST);
            commonResponse.setMessage("Cannot create empty record");
            commonResponse.setData(null);
        }

        log.info("BookServiceImpl.createBooks() method ended");

        return commonResponse;
    }

    @Override
    public CommonResponse<List<Books>> getBooksList() {
        log.info("BookServiceImpl.getBooksList() method started");
        CommonResponse<List<Books>> commonResponse = new CommonResponse<>();

        List<Books> booksList = booksRepository.findAll();

        if (!booksList.isEmpty()){
            List<BookListDTO> bookListDTOS = booksList.stream()
                    .map(book -> modelMapper.map(book, BookListDTO.class))
                    .collect(Collectors.toList());

            commonResponse.setMessage("Books List Retrieved Successfully");
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(bookListDTOS);
        }else {
            commonResponse.setMessage("No Books to Retrieve");
            commonResponse.setStatus(HttpStatus.NOT_FOUND);
            commonResponse.setData(null);
        }
        log.info("BookServiceImpl.getBooksList() method ended");
        return commonResponse;
    }

    @Override
    public CommonResponse<Books> getBookById(final Integer id) {
        log.info("BookServiceImpl.getBookById() method started");
        CommonResponse commonResponse = new CommonResponse();

        Optional<Books> book = booksRepository.findById(id);

        if (book.isPresent()){
            BookListDTO bookListDTO = modelMapper.map(book, BookListDTO.class);

            commonResponse.setMessage("Book found for given id");
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(bookListDTO);
        }else {
            commonResponse.setMessage("No book found for given id");
            commonResponse.setStatus(HttpStatus.NOT_FOUND);
            commonResponse.setData(null);
        }
        log.info("BookServiceImpl.getBookById() method ended");
        return commonResponse;
    }

    @Override
    public CommonResponse<Books> updateBook(final Books books, final Integer id) {
        log.info("BookServiceImpl.updateBook() method started");
        CommonResponse commonResponse = new CommonResponse();

        Optional<Books> book = booksRepository.findById(id);

        if (book.isPresent()){
            Books newBook = book.get();

            if (!books.getBookName().isEmpty()){
                newBook.setBookName(books.getBookName());
            }
            if (!books.getAuthorName().isEmpty()){
                newBook.setAuthorName(books.getAuthorName());
            }
            booksRepository.save(newBook);

            commonResponse.setMessage("Book updated successfully");
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(newBook);
        } else {
            commonResponse.setMessage("No book for given Id");
            commonResponse.setStatus(HttpStatus.NOT_FOUND);
            commonResponse.setData(null);
        }
        log.info("BookServiceImpl.updateBook() method ended");
        return commonResponse;
    }

    @Override
    public CommonResponse<Books> deleteBook(final Integer id) {
        log.info("BookServiceImpl.deleteBook() method started");
        CommonResponse commonResponse = new CommonResponse();

        Optional<Books> books = booksRepository.findById(id);

        if (books.isPresent()){
            Books deletedBook = books.get();
            booksRepository.delete(deletedBook);

            commonResponse.setMessage("Record deleted successfully");
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(null);
        }else {
            commonResponse.setMessage("No record found for given ID");
            commonResponse.setStatus(HttpStatus.NOT_FOUND);
            commonResponse.setData(null);
        }
        log.info("BookServiceImpl.deleteBook() method ended");
        return commonResponse;
    }

}

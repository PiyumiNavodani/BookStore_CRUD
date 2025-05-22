package com.book.bookstore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author by piyumi_navodani
 */
@Getter
@Setter
public class CommonResponse<T> {
    String message;
    HttpStatus status;
    Object data;
}

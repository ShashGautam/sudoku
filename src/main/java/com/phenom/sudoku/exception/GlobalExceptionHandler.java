package com.phenom.sudoku.exception;/*
 *
 *   Copyright (c) 2023 OLXAutos. - All Rights Reserved
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 *   @project sudoku by @author shashwatgautam on 02/07/23
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.rmi.ServerException;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

/**
 * The type Global exception handler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * The Request.
     */
    @Autowired
    HttpServletRequest request;

    /**
     * Handle exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle server exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({ServerException.class})
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public ResponseEntity<Object> handleServerException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), SERVICE_UNAVAILABLE);
    }

    /**
     * Handle unknown exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({Exception.class, })
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleUnknownException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle invalid sudoku format exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({InvalidSudokuFormatException.class, })
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<Object> handleInvalidSudokuFormatException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), ACCEPTED);
    }
}

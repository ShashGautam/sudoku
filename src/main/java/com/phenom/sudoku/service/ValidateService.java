package com.phenom.sudoku.service;/*
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.phenom.sudoku.exception.InvalidMoveException;
import com.phenom.sudoku.exception.SudokuNotReadyException;
import com.phenom.sudoku.model.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Validate service.
 */
@Service
public class ValidateService {

    /**
     * The Sudoku service.
     */
    @Autowired
    SudokuService sudokuService;

    /**
     * The Suggest service.
     */
    @Autowired
    SuggestService suggestService;

    /**
     * The New game service.
     */
    @Autowired
    NewGameService newGameService;

    /**
     * The Sudoku validate service.
     */
    @Autowired
    SudokuValidateService sudokuValidateService;


    /**
     * The Total retries.
     */
    @Value("${retryCount:4}")
    int totalRetries;

    /**
     * The Retry.
     */
    int retry;

    /**
     * Validate number string.
     *
     * @param row the row
     * @param col the col
     * @param val the val
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String validateNumber(int row, int col, int val) throws JsonProcessingException {
        if (sudokuService.getCurrSudoku() == null)
            throw new SudokuNotReadyException();
        String msg = "VALID";
        if (sudokuValidateService.isInvalid(row - 1, col - 1, val)) {
            msg = "INVALID";
            retry++;
        }
        if ("VALID".equals(msg)) {
            sudokuService.getCurrSudoku().getCellList()[row - 1][col - 1] = new Cell(row - 1, col - 1, val);
            retry = 0;
        } else if (retry == totalRetries) {
            retry = 0;
            return suggestService.suggestMove();
        }
        return msg;
    }

    /**
     * Validate move string.
     *
     * @param move the move
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String validateMove(String move) throws JsonProcessingException {
        if ("START".equals(move)) {
            newGameService.start();
            return "READY";
        } else throw new InvalidMoveException();
    }
}

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

import com.phenom.sudoku.model.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Sudoku validate service.
 */
@Service
public class SudokuValidateService {

    /**
     * The Sudoku service.
     */
    @Autowired
    SudokuService sudokuService;

    /**
     * Is invalid boolean.
     *
     * @param row the row
     * @param col the col
     * @param val the val
     * @return the boolean
     */
    public boolean isInvalid(int row, int col, int val) {
        boolean prevCheck = false, invalid = false;
        Cell rowCell, collCell, sqCell;
        for (int i = 0; i < 9; i++) {
            rowCell = sudokuService.getCurrSudoku().getCellList()[row][i];
            collCell = sudokuService.getCurrSudoku().getCellList()[i][col];
            if ((rowCell != null && val == rowCell.getValue()) ||
                    (collCell != null && val == collCell.getValue())) {
                invalid = true;
                prevCheck = true;
                break;
            }
        }
        if (!prevCheck) {
            row = row - row % 3;
            col = col - col % 3;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    sqCell = sudokuService.getCurrSudoku().getCellList()[row + i][col + j];
                    if (sqCell != null && val == sqCell.getValue()) {
                        invalid = true;
                        break;
                    }
                }
        }else prevCheck=false;

        return invalid;
    }
}

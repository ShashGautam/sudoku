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

import com.phenom.sudoku.model.Sudoku;
import org.springframework.stereotype.Service;

/**
 * The type Sudoku service.
 */
@Service
public class SudokuService {
    /**
     * The Curr sudoku.
     */
    Sudoku currSudoku;

    /**
     * Sets curr sudoku.
     *
     * @param sudoku the sudoku
     */
    public void setCurrSudoku(Sudoku sudoku) {
        currSudoku = sudoku;
    }

    /**
     * Gets curr sudoku.
     *
     * @return the curr sudoku
     */
    public Sudoku getCurrSudoku() {
        return currSudoku;
    }

    /**
     * Reset.
     */
    public void reset() {
        currSudoku = null;
    }

}

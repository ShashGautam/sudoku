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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phenom.sudoku.exception.CellNotEmptyException;
import com.phenom.sudoku.exception.InvalidSudokuException;
import com.phenom.sudoku.model.Cell;
import com.phenom.sudoku.model.Sudoku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Suggest service.
 */
@Service
public class SuggestService {

    /**
     * The Sudoku service.
     */
    @Autowired
    SudokuService sudokuService;

    /**
     * The Object mapper.
     */
    @Autowired
    ObjectMapper objectMapper;

    /**
     * The Sudoku validate service.
     */
    @Autowired
    SudokuValidateService sudokuValidateService;

    /**
     * Suggest move string.
     *
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String suggestMove() throws JsonProcessingException {
        Sudoku sudoku = objectMapper.readValue(objectMapper.writeValueAsString(sudokuService.getCurrSudoku()), Sudoku.class);
        int row = 0, col = 0;
        while (true) {
            Cell cell = findEmptyCell(0, row, col, sudoku.getCellList());
            for (int i = 1; i <= 9; i++)
                if (!sudokuValidateService.isInvalid(cell.getRow(), cell.getCol(), i)) {
                    row = cell.getRow() + 1;
                    col = cell.getCol() + 1;
                    return "Insert " + i + " at row:" + row + " and col:" + col;
                }

            if (col == 8) {
                if (row == 8)
                    throw new InvalidSudokuException();
                else {
                    col = 0;
                    row += 1;
                }

            } else col += 1;
        }
    }

    /**
     * Find empty cell cell.
     *
     * @param row   the row
     * @param col   the col
     * @param cells the cells
     * @return the cell
     */
    public Cell findEmptyCell(int val, int row, int col, Cell[][] cells) {
        for (int i = row; i < 9; i++)
            for (int j = col; j < 9; j++)
                if (cells[i][j] == null) {
                    cells[i][j] = new Cell(i, j, val);
                    return cells[i][j];
                }
        throw new CellNotEmptyException();
    }
}

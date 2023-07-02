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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phenom.sudoku.exception.InvalidSudokuFormatException;
import com.phenom.sudoku.model.Cell;
import com.phenom.sudoku.model.Sudoku;
import com.phenom.sudoku.repository.SudokuSkeletonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type New game service.
 */
@Service
public class NewGameService {
    /**
     * The Object mapper.
     */
    @Autowired
    ObjectMapper objectMapper;

    /**
     * The Sudoku skeleton repository.
     */
    @Autowired
    SudokuSkeletonRepository sudokuSkeletonRepository;

    /**
     * The Sudoku service.
     */
    @Autowired
    SudokuService sudokuService;

    /**
     * The Sudoku.
     */
    Sudoku sudoku;

    /**
     * Preset game.
     *
     * @param body the body
     */
    public void presetGame(String body) {
        sudokuService.setCurrSudoku(null);
        List<Cell> cellList;
        try {
            cellList = objectMapper.readValue(body, new TypeReference<>() {
            });
            sudoku = new Sudoku();
            sudoku.setCellList(new Cell[9][9]);
            cellList.forEach(cell ->
                    {
                        Cell[][] integerCellMap = sudoku.getCellList();
                        integerCellMap[cell.getRow()][cell.getCol()] = cell;
                    }
            );
        } catch (JsonProcessingException e) {
//                sudokuService.setCurrSudoku(objectMapper.readValue(objectMapper.writeValueAsString(sudokuSkeletonRepository.getSudoku()), Sudoku.class));
            throw new InvalidSudokuFormatException();
        }
        if (sudoku != null)
            sudokuSkeletonRepository.addSudoku(sudoku);
//            sudokuService.setCurrSudoku(objectMapper.readValue(objectMapper.writeValueAsString(sudoku), Sudoku.class));

    }

    /**
     * Start.
     */
    public void start() throws JsonProcessingException {
        if (sudoku == null)
            sudoku = sudokuSkeletonRepository.getSudoku();

        sudokuService.setCurrSudoku(objectMapper.readValue(objectMapper.writeValueAsString(sudoku), Sudoku.class));
    }
}

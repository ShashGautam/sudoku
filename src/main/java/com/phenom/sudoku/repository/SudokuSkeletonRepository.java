package com.phenom.sudoku.repository;/*
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

import com.phenom.sudoku.exception.SkeletonAbsentException;
import com.phenom.sudoku.model.Cell;
import com.phenom.sudoku.model.Sudoku;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The type Sudoku skeleton repository.
 */
@Repository
public class SudokuSkeletonRepository {

    private List<Sudoku> sudokuList;

    /**
     * Instantiates a new Sudoku skeleton repository.
     */
    public SudokuSkeletonRepository() {
        sudokuList = new ArrayList<>();
    }

    /**
     * Gets sudoku.
     *
     * @return the sudoku
     */
    public Sudoku getSudoku() {
        if (sudokuList.size() < 1)
            throw new SkeletonAbsentException();
        Random random = new Random();
        return sudokuList.get(random.nextInt(sudokuList.size()));
    }

    /**
     * Add sudoku.
     *
     * @param sudoku the sudoku
     */
    public void addSudoku(Sudoku sudoku) {
        if (sudokuList == null)
            sudokuList = new ArrayList<>();
        sudokuList.add(sudoku);
    }

    @PostConstruct
    private void addSampleSudoku() {
        Sudoku sudoku;
        for (int i = 0; i < 2; i++) {
            sudoku = new Sudoku();
            sudoku.setCellList(new Cell[9][9]);
            sudokuList.add(sudoku);
        }

        List<Cell> cellList = new LinkedList<>();
        cellList.add(new Cell(0,3,2));
        cellList.add(new Cell(0,4,6));
        cellList.add(new Cell(0,6,7));
        cellList.add(new Cell(0,8,1));
        cellList.add(new Cell(1,0,6));
        cellList.add(new Cell(1,1,8));
        cellList.add(new Cell(1,4,7));
        cellList.add(new Cell(1,7,9));
        cellList.add(new Cell(2,0,1));
        cellList.add(new Cell(2,1,9));
        cellList.add(new Cell(2,5,4));
        cellList.add(new Cell(2,6,5));
        cellList.add(new Cell(3,0,8));
        cellList.add(new Cell(3,1,2));
        cellList.add(new Cell(3,3,1));
        cellList.add(new Cell(3,7,4));
        cellList.add(new Cell(4,2,4));
        cellList.add(new Cell(4,3,6));
        cellList.add(new Cell(4,5,2));
        cellList.add(new Cell(4,6,9));
        cellList.add(new Cell(5,1,5));
        cellList.add(new Cell(5,5,3));
        cellList.add(new Cell(5,7,2));
        cellList.add(new Cell(5,8,8));
        cellList.add(new Cell(6,2,9));
        cellList.add(new Cell(6,3,3));
        cellList.add(new Cell(6,7,7));
        cellList.add(new Cell(6,8,4));
        cellList.add(new Cell(7,1,4));
        cellList.add(new Cell(7,4,5));
        cellList.add(new Cell(7,7,3));
        cellList.add(new Cell(7,8,6));
        cellList.add(new Cell(8,0,7));
        cellList.add(new Cell(8,2,3));
        cellList.add(new Cell(8,4,1));
        cellList.add(new Cell(8,5,8));

        cellList.forEach(cell -> sudokuList.get(0).getCellList()[cell.getRow()][cell.getCol()] = cell);
        cellList.clear();

        cellList.add(new Cell(0,0,1));
        cellList.add(new Cell(0,3,4));
        cellList.add(new Cell(0,4,8));
        cellList.add(new Cell(0,5,9));
        cellList.add(new Cell(0,8,6));
        cellList.add(new Cell(1,0,7));
        cellList.add(new Cell(1,1,3));
        cellList.add(new Cell(1,7,4));
        cellList.add(new Cell(2,5,1));
        cellList.add(new Cell(2,6,2));
        cellList.add(new Cell(2,7,9));
        cellList.add(new Cell(2,8,5));
        cellList.add(new Cell(3,2,7));
        cellList.add(new Cell(3,3,1));
        cellList.add(new Cell(3,4,2));
        cellList.add(new Cell(3,6,6));
        cellList.add(new Cell(4,0,5));
        cellList.add(new Cell(4,3,7));
        cellList.add(new Cell(4,5,3));
        cellList.add(new Cell(4,8,8));
        cellList.add(new Cell(5,2,6));
        cellList.add(new Cell(5,4,9));
        cellList.add(new Cell(5,5,5));
        cellList.add(new Cell(5,6,7));
        cellList.add(new Cell(6,0,9));
        cellList.add(new Cell(6,1,1));
        cellList.add(new Cell(6,2,4));
        cellList.add(new Cell(6,3,6));
        cellList.add(new Cell(7,1,2));
        cellList.add(new Cell(7,7,3));
        cellList.add(new Cell(7,8,7));
        cellList.add(new Cell(8,0,8));
        cellList.add(new Cell(8,3,5));
        cellList.add(new Cell(8,4,1));
        cellList.add(new Cell(8,5,2));
        cellList.add(new Cell(8,8,4));

        cellList.forEach(cell -> sudokuList.get(1).getCellList()[cell.getRow()][cell.getCol()] = cell);
        cellList.clear();
    }
}

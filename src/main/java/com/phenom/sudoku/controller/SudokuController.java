package com.phenom.sudoku.controller;/*
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
import com.phenom.sudoku.service.NewGameService;
import com.phenom.sudoku.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Sudoku controller.
 */
@RestController
@RequestMapping("/sudoku")
public class SudokuController {

    /**
     * The New game service.
     */
    @Autowired
    NewGameService newGameService;

    /**
     * The Validate service.
     */
    @Autowired
    ValidateService validateService;

    /**
     * Preset game.
     *
     * @param reqBody the req body
     */
    @PostMapping(path = "/presetGame")
    public void presetGame(@RequestBody(required = false) String reqBody) {
        newGameService.presetGame(reqBody);
    }

    /**
     * Initialize game string.
     *
     * @param reqBody the req body
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @PostMapping(path = "/initGame")
    public String initializeGame(@RequestBody(required = false) String reqBody) throws JsonProcessingException {
        return validateService.validateMove(reqBody);
    }

    /**
     * Insert num string.
     *
     * @param row the row
     * @param col the col
     * @param val the val
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(path = "/move/{row}/{col}")
    public String insertNum(@PathVariable int row, @PathVariable int col, @RequestBody String val) throws JsonProcessingException {
        return validateService.validateNumber(row, col, Integer.parseInt(val));
    }
}

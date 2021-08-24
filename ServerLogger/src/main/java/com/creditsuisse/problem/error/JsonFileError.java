/*
Class : LogAdder
Description: used to throw customized java exception
Author : Rohal Kurup
 */

package com.creditsuisse.problem.error;

public class JsonFileError extends RuntimeException {

    public JsonFileError(String errorMessage) {
        super(errorMessage);
    }
}

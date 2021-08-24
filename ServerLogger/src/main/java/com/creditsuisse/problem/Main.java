/*
Class : LogAdder
Description: Main class exposed to user
Author : Rohal Kurup
 */

package com.creditsuisse.problem;

import com.creditsuisse.problem.db.Database;
import com.creditsuisse.problem.error.JsonFileError;
import com.creditsuisse.problem.fileReader.JsonFileReader;
import com.creditsuisse.problem.logger.MyLogging;
import com.creditsuisse.problem.metadata.LogDetails;
import com.creditsuisse.problem.parsing.Parse;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws Exception {
        File filePath;
        Logger logger = MyLogging.getLogger();
        logger.info("Starting with processing of json files");

        try {
            String file = JsonFileReader.getFileNameAndPath();
            JsonFileReader jsonFileReader = new JsonFileReader(file);
            filePath = jsonFileReader.getFile();
        } catch (JsonFileError e) {
            logger.severe(e.toString());
            throw e;
        }
        Parse parser = new LogAdder();
        HashMap<String, LogDetails> map = parser.ReadFileAndInsertFileContentIntoMap(filePath);
        Database.insertIntoDB(map);

    }
}

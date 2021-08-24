package com.creditsuisse.problem.fileReader;

import com.creditsuisse.problem.error.JsonFileError;
import com.creditsuisse.problem.logger.MyLogging;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class JsonFileReader {

    private static final String defaultPath = "src/jsonFiles/log.json";
    private File file;
    private String filePath;

    public JsonFileReader() {

    }

    public JsonFileReader(String filePath) {
        if (file == null)
            this.file = new File(filePath);
    }

    public static String getFileNameAndPath() throws IOException {
        Logger logger = MyLogging.getLogger();
        String inputFilePath;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your option");
        System.out.println("1) Use the default jsonfile");
        System.out.println("2) Use a new json file");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 2:
                System.out.println("Please entry the file name with path");
                inputFilePath = sc.nextLine();
                JsonFileReader jsonFileReader = new JsonFileReader(inputFilePath);
                if (!jsonFileReader.checkJsonFile()) {
                    System.out.println("Json file is incorrect");
                    throw new JsonFileError("Json file is incorrect please enter correct json file");
                }
                if (!jsonFileReader.isJSONFileExists()) {
                    System.out.println("Json file does not exists");
                    throw new JsonFileError("Json file does not  exists");
                }

                logger.fine("file path to be used "+inputFilePath);
                return inputFilePath;
            case 1:
                new JsonFileReader(defaultPath);
                System.out.println("Using the default file " + defaultPath);
                logger.info("file path to be used "+defaultPath);
                return defaultPath;
            default:
                logger.info("Invalid option plese check the input passed");
                System.out.println("Entered option is invalid");
                throw new JsonFileError("Entered option is invalid");

        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private boolean checkJsonFile() {
        String jsonFile = this.file.getName();
        String ext = jsonFile.substring(jsonFile.lastIndexOf('.') + 1);
        return ext.equals("json");
    }

    private boolean isJSONFileExists() {
        return this.file.exists();
    }
}

package com.creditsuisse.problem.logger;

import java.io.IOException;
import java.util.Random;
import java.util.logging.*;

public class MyLogging {

    public static Logger logger;
    public Handler fileHandler;
    Formatter plainText;
    Random randomLogAppender = new Random();

    private MyLogging() throws IOException {
        logger = Logger.getLogger(MyLogging.class.getName());
        logger.setUseParentHandlers(false);
        fileHandler = new FileHandler("src/logs/ServerApplication.log" + randomLogAppender.nextInt(),false);
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.addHandler(fileHandler);
    }
    public static Logger getLogger() throws IOException {
        if(logger == null){
            new MyLogging();
        }
        return logger;
    }

    public static void log(Level level, String msg) throws IOException {
        getLogger().log(level, msg);
        System.out.println(msg);
    }
}
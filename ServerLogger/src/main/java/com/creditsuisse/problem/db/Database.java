/*
Class : LogAdder
Description: class to connect db and update entries
Author : Rohal Kurup
 */

package com.creditsuisse.problem.db;

import com.creditsuisse.problem.logger.MyLogging;
import com.creditsuisse.problem.metadata.LogDetails;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Database {
    public static Connection conn;
    public static String connectionString = "jdbc:hsqldb:file:db-data/hsqldatabase";


    public static void insertIntoDB(HashMap<String, LogDetails> map) throws Exception {
        Logger logger = MyLogging.getLogger();
        logger.info("Final insertion into database ");
        String createStatement = readToString("src/main/java/com/creditsuisse/problem/sql/create.sql");
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {

            throw e;
        }
        try {

            conn = DriverManager.getConnection(connectionString, "SA", "");
            conn.createStatement().executeUpdate(createStatement);
            String SQLStatement = "INSERT INTO LogEvent VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepStatement = conn.prepareStatement(SQLStatement);

            for (Map.Entry<String, LogDetails> entry : map.entrySet()) {
                logger.fine("data inserted into databases --> "+entry.getValue().getEventId()
                        +" "+(int) entry.getValue().getDuration()+" "+entry.getValue().getType()+
                        " "+entry.getValue().getHost()+" "+entry.getValue().getAlertFlag());
                prepStatement.setString(1, entry.getValue().getEventId());
                prepStatement.setInt(2, (int) entry.getValue().getDuration());
                prepStatement.setString(3, entry.getValue().getType());
                prepStatement.setString(4, entry.getValue().getHost());
                prepStatement.setBoolean(5,entry.getValue().getAlertFlag());

                System.out.println("Insert done");
                prepStatement.executeUpdate();


            }


        } catch (Exception e) {
            logger.severe(e.toString());
            throw e;
        } finally {
            if (conn != null)
                conn.close();

        }


    }

    public static String readToString(String fname) throws Exception {
        File file = new File(fname);
        String string = FileUtils.readFileToString(file, "utf-8");
        return string;
    }


}


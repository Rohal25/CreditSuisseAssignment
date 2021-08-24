/*
Class : LogAdder
Description: Takes data from json file and insert it into hashmap
Author : Rohal Kurup
 */


package com.creditsuisse.problem;


import com.creditsuisse.problem.logger.MyLogging;
import com.creditsuisse.problem.metadata.LogDetails;
import com.creditsuisse.problem.parsing.Parse;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class LogAdder implements Parse {
    //HashMap used for faster retrieval and duplicate entries for
    // same id will not be stored so memory is saved
    private static final HashMap<String, LogDetails> map = new HashMap();
    private static final int longerDuration = 4;
    private File file;

    private static String getHost(String checkLog, JSONObject obj) {
        if (!checkLog.contains("host")) {
            return "NA";
        } else {
            return obj.getString("host");
        }
    }

    private static String getType(String checkLog, JSONObject obj) {
        if (!checkLog.contains("type")) {
            return "NA";
        } else {
            return obj.getString("type");
        }
    }

    private static void computeDurationAndFlag(JSONObject obj, String id, LogDetails logDetails) {

        long time1 = map.get(id).getTimestamp();
        long time2 = obj.getLong("timestamp");
        long duration = Math.abs(time1 - time2);
        logDetails.setDuration(duration);
        if (duration > longerDuration) {
            logDetails.setAlertFlag(true);
        }
    }

    public void insertIntoMap(Object object) {
        JSONObject obj = (JSONObject) object;
        String checkLog = obj.toString();
        long difference = 0l;
        boolean flag = false;
        String id = (String) obj.get("id");
        LogDetails logDetails = new LogDetails();
        logDetails.setHost(getHost(checkLog, obj));
        logDetails.setType(getType(checkLog, obj));
        logDetails.setEventId(id);

        if (map.containsKey(id)) {
            computeDurationAndFlag(obj, id, logDetails);
        }
        else
        {
            logDetails.setTimestamp(obj.getLong("timestamp"));
        }
        map.put(id, logDetails);
    }

    public HashMap<String, LogDetails> ReadFileAndInsertFileContentIntoMap(File fileName) throws IOException {
        Logger logger = MyLogging.getLogger();
        logger.info("Starting inserting json file content into HashMap....................");
        this.file = fileName;
        Pattern pattern = Pattern.compile("}");
        Scanner input = null;
        try {
            input = new Scanner(file).useDelimiter(pattern);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (input.hasNext()) {
            String line = input.next().trim();
            JSONObject obj = new JSONObject(line + "}");
            insertIntoMap(obj);

        }
        logger.info("Completed inserting json file content into HashMap....................");
        return map;
    }
}

package org.organizerweb.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that represents a logger.
 * It is used to store the logs of the application.
 *
 * @version 1.0
 */
public class Logger
{
    private final ArrayList<String> logs = new ArrayList<String>();

    public void log(String message) {
        logs.add(LocalDateTime.now().toString() + " " + message);
    }

    public void addLog(String message) {
        logs.add(message);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }
}

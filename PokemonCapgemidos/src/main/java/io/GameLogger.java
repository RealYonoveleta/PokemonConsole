package io;

import java.util.LinkedList;
import java.util.Queue;

public class GameLogger {
	
	private static final ConsoleHandler console = ConsoleHandler.getInstance();
	
	private static GameLogger instance = new GameLogger();
    private final Queue<String> logQueue;

    private GameLogger() {
        logQueue = new LinkedList<>();
    }

    public static GameLogger getInstance() {
        return instance;
    }

    public void log(String message) {
        logQueue.add(message);
    }
    
    public void log(String format, Object... args) {
        log(String.format(format, args));
    }

    public void flushLogs() {
        while (!logQueue.isEmpty()) {
            console.displayMessage(logQueue.poll());
        }
    }

}

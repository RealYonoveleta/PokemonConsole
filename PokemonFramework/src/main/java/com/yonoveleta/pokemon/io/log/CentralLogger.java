package com.yonoveleta.pokemon.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CentralLogger {
    
    private static Logger getLogger() {
    	// Get the caller's class name using the stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callingClass = stackTrace[3].getClassName();
        return LoggerFactory.getLogger(callingClass);
    }

    // Optional: You can create methods here to handle different log levels
    public static void logError(String message, Throwable throwable) {
        getLogger().error(message, throwable);
    }

    public static void logInfo(String message) {
        getLogger().info(message);
    }
    
    public static void logInfo(String format, Object... args) {
      getLogger().info(String.format(format, args));
    }

    public static void logWarn(String message) {
        getLogger().warn(message);
    }

    public static void logDebug(String message) {
        getLogger().debug(message);
    }
    
}

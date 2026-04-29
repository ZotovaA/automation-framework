package automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(Log.class);

    public static Logger getLogData(String className) {
        return LogManager.getLogger(className);
    }

    public static void startTest(String testName){
        logger.info("Test called: " + testName + " has started");
    }

    public static void endTest(String testName){
        logger.info("Test called: " + testName + " has ended");
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void warn(String message){
        logger.warn(message);
    }

    public static void error(String message) { logger.error(message);
    }
    public static void debug(String message){ logger.debug(message); }
}

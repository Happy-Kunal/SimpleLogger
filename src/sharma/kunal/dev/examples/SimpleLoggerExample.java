package sharma.kunal.dev.examples;

import sharma.kunal.simplelogger.*;
import sharma.kunal.simplelogger.exceptions.*;

public class SimpleLoggerExample {
    public static void main(String[] args) throws InterruptedException {
        try {
            SimpleLoggerFactory.setDefaultLogFormatter(new DefaultLogFormatter());
            SimpleLoggerFactory.setLoggingLevel(LoggingLevel.WARN);

            SimpleLoggerInterface logger1 = SimpleLoggerFactory.getLogger();
            SimpleLoggerInterface logger2 = SimpleLoggerFactory.getLogger("MyNamedLogger");

            final String logMessage = "Hello SimpleLogger";

            logger1.debug(logMessage);
            logger1.info(logMessage);
            logger1.warn(logMessage);
            logger1.error(logMessage);
            logger1.critical(logMessage);

            Thread.sleep(1000);

            logger2.debug(logMessage);
            logger2.info(logMessage);
            logger2.warn(logMessage);
            logger2.error(logMessage);
            logger2.critical(logMessage);
        } catch (DefaultLogFormatterAlreadySetException | LogFormatterNotSetException | LoggingLevelAlreadySetException e) {
            // these exceptions will never be raised
            // for code snipet given above

            System.out.println("Exception ocuured while creating logger");
        }
    }
    
}

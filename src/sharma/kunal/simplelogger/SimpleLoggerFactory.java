package sharma.kunal.simplelogger;

import sharma.kunal.simplelogger.exceptions.LogFormatterAlreadySetException;
import sharma.kunal.simplelogger.exceptions.LogFormatterNotSetException;

public class SimpleLoggerFactory {
    // TODO: add logging level
    // TODO: Support Synchronized logging for multithreaded program
    // TODO: implement named Simple Logger
    
    private static boolean init = false;
    private static int simpleLoggersProduced = 0;
    private static LogFormatter lfmt;

    private SimpleLoggerFactory() {}

    public static void setLogFormatter(LogFormatter lfmt) throws LogFormatterAlreadySetException {
        if (!init) {
            init = true;
            SimpleLoggerFactory.lfmt = lfmt;
        } else {
            throw new LogFormatterAlreadySetException();
        }
    }

    public static SimpleLoggerInterface getLogger() throws LogFormatterNotSetException {
        if (!init) throw new LogFormatterNotSetException();
        return new SimpleLogger(simpleLoggersProduced++);
    }

    private static class SimpleLogger implements SimpleLoggerInterface {
        private int id;
        private static String simpleLoggerFormatString = "[SimpleLogger %04d]%s";
        
        SimpleLogger(int id) {
            this.id = id;
        }

        public void debug(String msg) {
            final String logString = String.format(simpleLoggerFormatString, id, lfmt.debug(msg));
            System.out.println(logString);
        }

        public void info(String msg) {
            final String logString = String.format(simpleLoggerFormatString, id, lfmt.info(msg));
            System.out.println(logString);
        }

        public void warn(String msg) {
            final String logString = String.format(simpleLoggerFormatString, id, lfmt.warn(msg));
            System.out.println(logString);
        }

        public void error(String msg) {
            final String logString = String.format(simpleLoggerFormatString, id, lfmt.error(msg));
            System.out.println(logString);
        }

        public void critical(String msg) {
            final String logString = String.format(simpleLoggerFormatString, id, lfmt.critical(msg));
            System.out.println(logString);
        }

    }
}
package sharma.kunal.simplelogger;

import java.io.PrintStream;

import sharma.kunal.simplelogger.exceptions.*;

public class SimpleLoggerFactory {
    // FEATURE: Supports logging level
    // FEATURE: Supports Diffent LogFiles For Different Kind of Threads
    // FEATURE: Supports Different LogFormatting For Some Odd Logging Requirements
    // FEATURE: Supports Synchronized logging for multithreaded program
    // FEATURE: Supports named Logger
    
    private static boolean initLogFormatter = false;
    private static boolean initLoggingLevel = false;
    private static int simpleLoggersProduced = 0;
    private static PrintStream pstrm = System.out;
    
    private static LogFormatter lfmt;
    private static LoggingLevel loggingLevel = LoggingLevel.NONE;


    private SimpleLoggerFactory() {}

    public static void setDefaultLogFormatter(LogFormatter lfmt) throws DefaultLogFormatterAlreadySetException {
        if (!initLogFormatter) {
            initLogFormatter = true;
            SimpleLoggerFactory.lfmt = lfmt;
        } else {
            throw new DefaultLogFormatterAlreadySetException();
        }
    }

    public static void setDefaultPrintStream(PrintStream pstrm) {
        SimpleLoggerFactory.pstrm = pstrm;
    }

    public static void setLoggingLevel(LoggingLevel loggingLevel) throws LoggingLevelAlreadySetException {
        if (!initLoggingLevel) {
            initLoggingLevel = true;
            SimpleLoggerFactory.loggingLevel = loggingLevel;
        } else {
            throw new LoggingLevelAlreadySetException();
        }
    }

    
    public static synchronized SimpleLoggerInterface getLogger() throws LogFormatterNotSetException {
        if (!initLogFormatter) throw new LogFormatterNotSetException();
        return new SimpleLogger("SimpleLogger", simpleLoggersProduced++, pstrm, lfmt);
    }

    public static synchronized SimpleLoggerInterface getLogger(String name) throws LogFormatterNotSetException {
        return SimpleLoggerFactory.getLogger(name, pstrm, lfmt);
    }

    public static synchronized SimpleLoggerInterface getLogger(PrintStream pstrm) throws LogFormatterNotSetException {
        return SimpleLoggerFactory.getLogger("SimpleLogger", pstrm, lfmt);
    }

    public static synchronized SimpleLoggerInterface getLogger(String name, PrintStream pstrm) throws LogFormatterNotSetException {
        return SimpleLoggerFactory.getLogger(name, pstrm, lfmt);
    }

    public static synchronized SimpleLoggerInterface getLogger(String name, PrintStream pstrm, LogFormatter lfmt) throws LogFormatterNotSetException {
        if (!initLogFormatter) throw new LogFormatterNotSetException();
        return new SimpleLogger(name, simpleLoggersProduced++, pstrm, lfmt);
    }


    // SimpleLogger class
    private static class SimpleLogger implements SimpleLoggerInterface {
        private String loggerSignature;
        private PrintStream pstrm = System.out;

        private LogFormatter lfmt;

        SimpleLogger(String name, int id, PrintStream pstrm, LogFormatter lfmt) {
            this.pstrm = pstrm;
            this.lfmt = lfmt;

            this.loggerSignature = String.format("%s (logger id: %04d)", name, id);
        }

        public void debug(String msg) {
            if (SimpleLoggerFactory.loggingLevel.canLog(LoggingLevel.DEBUG)) {
                final String logString = lfmt.debug(loggerSignature, msg);
                synchronized (pstrm) {
                    pstrm.println(logString);
                }
            }
        }

        public void info(String msg) {
            if (SimpleLoggerFactory.loggingLevel.canLog(LoggingLevel.INFO)) {
                final String logString = lfmt.info(loggerSignature, msg);
                synchronized (pstrm) {
                    pstrm.println(logString);
                }
            }
        }

        public void warn(String msg) {
            if (SimpleLoggerFactory.loggingLevel.canLog(LoggingLevel.WARN)) {
                final String logString = lfmt.warn(loggerSignature, msg);
                synchronized (pstrm) {
                    pstrm.println(logString);
                }
            }
        }

        public void error(String msg) {
            if (SimpleLoggerFactory.loggingLevel.canLog(LoggingLevel.ERROR)) {
                final String logString = lfmt.error(loggerSignature, msg);
                synchronized (pstrm) {
                    pstrm.println(logString);
                }
            }
        }

        public void critical(String msg) {
            if (SimpleLoggerFactory.loggingLevel.canLog(LoggingLevel.CRITICAL)) {
                final String logString = lfmt.critical(loggerSignature, msg);
                synchronized (pstrm) {
                    pstrm.println(logString);
                }
            }
        }
    }
}

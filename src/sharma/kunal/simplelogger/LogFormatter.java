package sharma.kunal.simplelogger;

import java.util.Date;

public interface LogFormatter {
    public default String defaultLogger(String loggerSignature, String msg) {
        String datetime = new Date().toString();
        return String.format("[%s][%s]: %s", datetime, loggerSignature, msg);
    }

    public default String debug(String loggerSignature, String msg) {
        return "[DEBUG]" + defaultLogger(loggerSignature, msg);
    }

    public default String info(String loggerSignature, String msg) {
        return "[INFO]" + defaultLogger(loggerSignature, msg);
    }

    public default String warn(String loggerSignature, String msg) {
        return "[WARN]" + defaultLogger(loggerSignature, msg);
    }

    public default String error(String loggerSignature, String msg) {
        return "[ERROR]" + defaultLogger(loggerSignature, msg);
    }

    public default String critical(String loggerSignature, String msg) {
        return "[CRITICAL]" + defaultLogger(loggerSignature, msg);
    }
}
package sharma.kunal.simplelogger;

import java.util.Date;

public interface LogFormatter {
    public default String defaultLogger(String msg) {
        String datetime = new Date().toString();
        return String.format("[%s]: %s", datetime, msg);
    }

    public default String debug(String msg) {
        return "[DEBUG]" + defaultLogger(msg);
    }

    public default String info(String msg) {
        return "[INFO]" + defaultLogger(msg);
    }

    public default String warn(String msg) {
        return "[WARN]" + defaultLogger(msg);
    }

    public default String error(String msg) {
        return "[ERROR]" + defaultLogger(msg);
    }

    public default String critical(String msg) {
        return "[CRITICAL]" + defaultLogger(msg);
    }
}
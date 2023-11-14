package sharma.kunal.simplelogger;

public enum LoggingLevel {
    DEBUG, INFO, WARN, ERROR, CRITICAL, NONE;

    public boolean canLog(LoggingLevel other) {
        return this.ordinal() <= other.ordinal();
    }
}

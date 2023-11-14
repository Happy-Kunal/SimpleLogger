package sharma.kunal.dev.examples;

import sharma.kunal.simplelogger.LogFormatter;

class MyLogger implements LogFormatter {
}

class LogFormatterExample {
    public static void main(String[] args) {
        final String message = "This is an Example String";
        LogFormatter logger = new MyLogger();

        System.out.println(logger.defaultLogger("TEMP", message));
        System.out.println(logger.debug("TEMP", message));
        System.out.println(logger.info("TEMP", message));
        System.out.println(logger.warn("TEMP", message));
        System.out.println(logger.error("TEMP", message));
        System.out.println(logger.critical("TEMP", message));
    }
}
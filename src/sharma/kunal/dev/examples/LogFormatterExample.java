package sharma.kunal.dev.examples;

import sharma.kunal.simplelogger.LogFormatter;

class MyLogger implements LogFormatter {
}

class LogFormatterExample {
    public static void main(String[] args) {
        final String message = "This is an Example String";
        LogFormatter logger = new MyLogger();

        System.out.println(logger.defaultLogger(message));
        System.out.println(logger.debug(message));
        System.out.println(logger.info(message));
        System.out.println(logger.warn(message));
        System.out.println(logger.error(message));
        System.out.println(logger.critical(message));
    }
}
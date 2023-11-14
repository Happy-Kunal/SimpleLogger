package sharma.kunal.dev.examples;

import sharma.kunal.simplelogger.*;
import sharma.kunal.simplelogger.exceptions.*;

class Worker implements Runnable {
    private String name;
    private Thread workerThread;

    Worker(String name) {
        this.name = name;
        workerThread = new Thread(this, name);
    }

    public void run() {
        SimpleLoggerInterface logger;
        try {
            logger = SimpleLoggerFactory.getLogger(this.name + "Logger");
        } catch (LogFormatterNotSetException e) {
            System.err.println(e + " Occured While Trying To create logger instance");
            return;
        }

        final String logMessageString = "Hello SimpleLogger From " + name;

        try {
            logger.debug(logMessageString);
            Thread.sleep(200);
            logger.info(logMessageString);
            Thread.sleep(200);
            logger.warn(logMessageString);
            Thread.sleep(200);
            logger.error(logMessageString);
            Thread.sleep(200);
            logger.critical(logMessageString);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.err.println(e + "occured in " + name);
            Thread.currentThread().interrupt();
        }
    }

    public void start() {
        workerThread.start();
    }

    public void join() throws InterruptedException {
        workerThread.join();
    }
}

public class MultiThreadedSimpleLoggerExample {
    public static void main(String[] args) {
        try {
            SimpleLoggerFactory.setDefaultLogFormatter(new DefaultLogFormatter());
            SimpleLoggerFactory.setLoggingLevel(LoggingLevel.WARN);

            Worker worker1 = new Worker("worker1");
            Worker worker2 = new Worker("worker2");

            worker1.start();
            worker2.start();

            worker1.join();
            worker2.join();
        } catch (DefaultLogFormatterAlreadySetException | LoggingLevelAlreadySetException e) {
            // these exceptions will never be raised
            // for code snipet given above

            System.out.println("Exception ocuured while creating logger");
        } catch (InterruptedException e) {
            System.err.println(e + "occured while performing join() on worker threads");
            Thread.currentThread().interrupt();            
        }
    }
    
}

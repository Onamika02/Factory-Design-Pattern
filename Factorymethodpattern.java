abstract class LoggerFactoryMethod {
    public abstract Logger createLogger();
}

class ConsoleLoggerFactory extends LoggerFactoryMethod {
    @Override
    public Logger createLogger() {
        return new ConsoleLogger();
    }
}

class FileLoggerFactory extends LoggerFactoryMethod {
    private String filename;

    public FileLoggerFactory(String filename) {
        this.filename = filename;
    }

    @Override
    public Logger createLogger() {
        return new FileLogger(filename);
    }
}

public class Factorymethodpattern {
    public static void main(String[] args) {
        LoggerFactoryMethod consoleFactory = new ConsoleLoggerFactory();
        Logger consoleLogger = consoleFactory.createLogger();
        consoleLogger.log("This is a console log message using Factory Method.");

        LoggerFactoryMethod fileFactory = new FileLoggerFactory("log.txt");
        Logger fileLogger = fileFactory.createLogger();
        fileLogger.log("This is a file log message using Factory Method.");
    }
}

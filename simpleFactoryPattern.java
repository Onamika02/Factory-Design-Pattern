abstract class Logger {
    public abstract void log(String message);
}

class ConsoleLogger extends Logger {
    @Override
    public void log(String message) {
        System.out.println("Console Log: " + message);
    }
}

class FileLogger extends Logger {
    private String filename;

    public FileLogger(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        try (java.io.FileWriter fw = new java.io.FileWriter(filename, true)) {
            fw.write("File Log: " + message + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

class LoggerFactory {
    public static Logger getLogger(String loggerType, String filename) {
        if ("console".equalsIgnoreCase(loggerType)) {
            return new ConsoleLogger();
        } else if ("file".equalsIgnoreCase(loggerType) && filename != null) {
            return new FileLogger(filename);
        } else {
            throw new IllegalArgumentException("Unknown logger type or missing filename for file logger.");
        }
    }
}

public class simpleFactoryPattern{
    public static void main(String[] args) {
        Logger consoleLogger = LoggerFactory.getLogger("console", null);
        consoleLogger.log("This is a console log message.");

        Logger fileLogger = LoggerFactory.getLogger("file", "log.txt");
        fileLogger.log("This is a file log message.");
    }
}

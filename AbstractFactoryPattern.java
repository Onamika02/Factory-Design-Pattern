abstract class ErrorLog extends Logger {}
abstract class InfoLog extends Logger {}
abstract class WarningLog extends Logger {}

class ConsoleErrorLog extends ErrorLog {
    @Override
    public void log(String message) {
        System.out.println("Console Error Log: " + message);
    }
}

class ConsoleInfoLog extends InfoLog {
    @Override
    public void log(String message) {
        System.out.println("Console Info Log: " + message);
    }
}

class ConsoleWarningLog extends WarningLog {
    @Override
    public void log(String message) {
        System.out.println("Console Warning Log: " + message);
    }
}

class FileErrorLog extends ErrorLog {
    private String filename;

    public FileErrorLog(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        try (java.io.FileWriter fw = new java.io.FileWriter(filename, true)) {
            fw.write("File Error Log: " + message + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

class FileInfoLog extends InfoLog {
    private String filename;

    public FileInfoLog(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        try (java.io.FileWriter fw = new java.io.FileWriter(filename, true)) {
            fw.write("File Info Log: " + message + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

class FileWarningLog extends WarningLog {
    private String filename;

    public FileWarningLog(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        try (java.io.FileWriter fw = new java.io.FileWriter(filename, true)) {
            fw.write("File Warning Log: " + message + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

abstract class LoggerAbstractFactory {
    public abstract ErrorLog createErrorLogger();
    public abstract InfoLog createInfoLogger();
    public abstract WarningLog createWarningLogger();
}


class ConsoleLoggerAbstractFactory extends LoggerAbstractFactory {
    @Override
    public ErrorLog createErrorLogger() {
        return new ConsoleErrorLog();
    }

    @Override
    public InfoLog createInfoLogger() {
        return new ConsoleInfoLog();
    }

    @Override
    public WarningLog createWarningLogger() {
        return new ConsoleWarningLog();
    }
}

class FileLoggerAbstractFactory extends LoggerAbstractFactory {
    private String filename;

    public FileLoggerAbstractFactory(String filename) {
        this.filename = filename;
    }

    @Override
    public ErrorLog createErrorLogger() {
        return new FileErrorLog(filename);
    }

    @Override
    public InfoLog createInfoLogger() {
        return new FileInfoLog(filename);
    }

    @Override
    public WarningLog createWarningLogger() {
        return new FileWarningLog(filename);
    }
}

// Usage
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        LoggerAbstractFactory consoleFactory = new ConsoleLoggerAbstractFactory();
        ErrorLog consoleErrorLogger = consoleFactory.createErrorLogger();
        consoleErrorLogger.log("This is an error message for console logger using Abstract Factory.");

        LoggerAbstractFactory fileFactory = new FileLoggerAbstractFactory("log.txt");
        InfoLog fileInfoLogger = fileFactory.createInfoLogger();
        fileInfoLogger.log("This is an info message for file logger using Abstract Factory.");
    }
}

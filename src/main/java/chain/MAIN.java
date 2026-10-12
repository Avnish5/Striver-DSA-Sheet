package chain;

public class MAIN {

    public static void main(String[] args) {
        LogProcessor logProcessor = getChainOfLoggers();
        System.out.println("Logging messages:");
        System.out.println("===== Logging DEBUG message =====");
        logProcessor.logMessage(LogProcessor.DEBUG, "This is a debug message");
        System.out.println("===== Logging INFO message =====");
        logProcessor.logMessage(LogProcessor.INFO, "This is an info message");
        System.out.println("===== Logging ERROR message =====");
        logProcessor.logMessage(LogProcessor.ERROR, "This is an error message");
        System.out.println("===== Logging FATAL message =====");
        logProcessor.logMessage(6, "This is a fatal message");
    }

    public static LogProcessor getChainOfLoggers() {
        LogProcessor debugLogProcessor = new DebugLogProcessor(LogProcessor.DEBUG);
        LogProcessor infoLogProcessor = new InfoLogProcessor(LogProcessor.INFO);
        LogProcessor fatalLogProcessor = new FatalLogProcessor(LogProcessor.FATAL);
        LogProcessor errorLogProcessor = new ErrorLogProcessor(LogProcessor.ERROR);

        debugLogProcessor.setNextLogProcessor(infoLogProcessor);
        infoLogProcessor.setNextLogProcessor(fatalLogProcessor);
        fatalLogProcessor.setNextLogProcessor(errorLogProcessor);

        return debugLogProcessor;
    }
}

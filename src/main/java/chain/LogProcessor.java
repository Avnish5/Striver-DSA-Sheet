package chain;

public  abstract  class LogProcessor {

    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int FATAL = 3;
    public static final int ERROR = 4;
    int level;
    LogProcessor nextLogProcessor;

    public void setNextLogProcessor(LogProcessor nextLogProcessor) {
        this.nextLogProcessor = nextLogProcessor;
    }


    public void logMessage(int level, String message) {
        if(this.level == level) {
            write(message);
            return;
        }

        this.nextLogProcessor.logMessage(level, message);
    }

    abstract protected void write(String message);
}

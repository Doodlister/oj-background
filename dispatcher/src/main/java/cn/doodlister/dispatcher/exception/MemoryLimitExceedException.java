package cn.doodlister.dispatcher.exception;



public class MemoryLimitExceedException extends Exception {
    public MemoryLimitExceedException() {
    }

    public MemoryLimitExceedException(String message) {
        super(message);
    }
}

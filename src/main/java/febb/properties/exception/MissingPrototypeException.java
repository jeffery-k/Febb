package febb.properties.exception;

public class MissingPrototypeException extends RuntimeException {
    public MissingPrototypeException(String prototypeName) {
        super("Missing Prototype [" + prototypeName + "]");
    }
}

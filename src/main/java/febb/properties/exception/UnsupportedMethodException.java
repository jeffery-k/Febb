package febb.properties.exception;

public class UnsupportedMethodException extends RuntimeException {
    public UnsupportedMethodException(Class klass) {
        super("Method unsupported by " + klass.toString());
    }

    public UnsupportedMethodException(Class klass, String operation) {
        super(operation + " unsupported by " + klass.toString());
    }
}

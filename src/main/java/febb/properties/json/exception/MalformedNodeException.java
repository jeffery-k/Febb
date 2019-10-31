package febb.properties.json.exception;

public class MalformedNodeException extends RuntimeException {
    public MalformedNodeException(Class nodeClass) {
        super("Could not construct node of type " + nodeClass);
    }
}

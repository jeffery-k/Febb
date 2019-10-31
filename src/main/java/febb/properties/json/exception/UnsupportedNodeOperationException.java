package febb.properties.json.exception;

public class UnsupportedNodeOperationException extends RuntimeException{
    public UnsupportedNodeOperationException(Class nodeClass, String operation) {
        super(operation + " unsupported by " + nodeClass);
    }
}

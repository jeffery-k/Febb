package febb.properties.json;

public class UnsupportedNodeOperationException extends RuntimeException{
    public UnsupportedNodeOperationException(Node node, String operation) {
        super(operation + " unsupported by " + node);
    }
}

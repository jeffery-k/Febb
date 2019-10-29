package febb.properties.json;

public abstract class Node {
    private final static String GET_OPERATION_STRING = "GET OPERATION";
    private final static String KEYS_OPERATION_STRING = "KEYS OPERATION";

    public Node get(String key) {
        throw new UnsupportedNodeOperationException(this, GET_OPERATION_STRING);
    }

    public Iterable<String> getKeys() {
        throw new UnsupportedNodeOperationException(this, KEYS_OPERATION_STRING);
    }

    public boolean isNull() {
        return false;
    }

    public String getValue() {
        return toString();
    }
}

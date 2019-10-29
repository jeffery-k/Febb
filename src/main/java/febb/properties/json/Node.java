package febb.properties.json;

import java.util.Iterator;

public abstract class Node implements Iterable<Node>{
    private static final String GET_OPERATION_STRING = "GET OPERATION";
    private static final String KEYS_OPERATION_STRING = "KEYS OPERATION";
    private static final String ITERATE_OPERATION_STRING = "ITERATE OPERATION";
    private static final String STRING_VALUE_OPERATION_STRING = "STRING_VALUE OPERATION";
    private static final String NUMBER_VALUE_OPERATION_STRING = "NUMBER_VALUE OPERATION";

    public Node get(String key) {
        throw new UnsupportedNodeOperationException(this, GET_OPERATION_STRING);
    }

    public Node get(int key) {
        throw new UnsupportedNodeOperationException(this, GET_OPERATION_STRING);
    }

    public Iterable<String> getKeys() {
        throw new UnsupportedNodeOperationException(this, KEYS_OPERATION_STRING);
    }

    public boolean isNull() {
        return false;
    }

    public String getStringValue() {
        throw new UnsupportedNodeOperationException(this, STRING_VALUE_OPERATION_STRING);
    }

    public double getNumberValue() {
        throw new UnsupportedNodeOperationException(this, NUMBER_VALUE_OPERATION_STRING);
    }

    @Override
    public Iterator<Node> iterator() {
        throw new UnsupportedNodeOperationException(this, ITERATE_OPERATION_STRING);
    }
}

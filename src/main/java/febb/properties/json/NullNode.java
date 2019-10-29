package febb.properties.json;

public class NullNode extends Node {
    private final static String NULL_STRING = "null";

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getValue() {
        return NULL_STRING;
    }
}

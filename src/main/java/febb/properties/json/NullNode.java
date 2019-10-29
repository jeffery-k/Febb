package febb.properties.json;

public class NullNode extends Node {
    private static final String NULL_STRING = "null";

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getStringValue() {
        return NULL_STRING;
    }
}

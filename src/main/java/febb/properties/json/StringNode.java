package febb.properties.json;

public class StringNode extends Node {
    private String value;

    public StringNode(String string) {
        this.value = string;
    }

    @Override
    public String getStringValue() {
        return value;
    }
}

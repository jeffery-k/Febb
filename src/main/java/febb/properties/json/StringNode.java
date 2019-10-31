package febb.properties.json;

public class StringNode extends Node {
    private String string;

    public StringNode(String string) {
        this.string = string;
    }

    @Override
    public String getStringValue() {
        return string;
    }
}

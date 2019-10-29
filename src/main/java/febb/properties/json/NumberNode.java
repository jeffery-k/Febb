package febb.properties.json;

public class NumberNode extends Node {
    private double number;

    @Override
    public String getValue() {
        return String.valueOf(number);
    }
}

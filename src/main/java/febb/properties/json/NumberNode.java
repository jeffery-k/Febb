package febb.properties.json;

public class NumberNode extends Node {
    private double number;

    public NumberNode(String string) {
        this.number = Double.parseDouble(string);
    }

    @Override
    public double getNumberValue() {
        return number;
    }
}

package febb.properties.json;

public class NumberNode extends Node {
    private double value;

    public NumberNode(String string) {
        this.value = Double.parseDouble(string);
    }

    @Override
    public double getDoubleValue() {
        return value;
    }

    @Override
    public int getCeilIntegerValue() {
        return (int) Math.ceil(value);
    }

    @Override
    public int getFloorIntegerValue() {
        return (int) Math.floor(value);
    }
}

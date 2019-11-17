package febb.properties.json;

class StringNode extends Node {
    private String value;

    StringNode(String string) {
        this.value = string;
    }

    @Override
    public String getStringValue() {
        return value;
    }
}

package febb.properties.json;

class StringNode extends Node {
    private String value;

    StringNode(String string) {
        this.value = string;
        this.nodeType = NodeType.STRING;
    }

    @Override
    public String getStringValue() {
        return value;
    }
}

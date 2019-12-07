package febb.properties.json;

import java.util.ArrayList;

class NullNode extends Node {
    public NullNode() {
        this.nodeType = NodeType.NULL;
    }

    @Override
    public Node get(String key) {
        return new NullNode();
    }

    @Override
    public Node get(int index) {
        return new NullNode();
    }

    @Override
    public Iterable<String> getKeys() {
        return new ArrayList<String>();
    }

    @Override
    public String getStringValue() {
        return "";
    }

    @Override
    public double getDoubleValue() {
        return 0;
    }

    @Override
    public int getIntegerValue() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int getCeilIntegerValue() {
        return 0;
    }

    @Override
    public int getFloorIntegerValue() {
        return 0;
    }

    @Override
    public boolean getBooleanValue() {
        return false;
    }
}

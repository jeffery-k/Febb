package febb.properties.json;

import febb.properties.json.exception.MalformedNodeException;

public class BooleanNode extends Node {
    private static final String REGEX_TRUE = "true";
    private static final String REGEX_FALSE = "false";

    private boolean value;
    public BooleanNode(String string) {
        if (string.matches(REGEX_TRUE)) {
            value = true;
        } else if (string.matches(REGEX_FALSE)) {
            value = false;
        } else {
            throw new MalformedNodeException(this.getClass());
        }
    }

    @Override
    public boolean getBooleanValue() {
        return value;
    }
}

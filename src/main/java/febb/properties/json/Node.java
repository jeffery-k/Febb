package febb.properties.json;

import febb.properties.json.exception.MalformedNodeException;
import febb.properties.json.exception.UnsupportedNodeOperationException;

public abstract class Node {
    private static final String GET_OPERATION_STRING = "GET OPERATION";
    private static final String KEYS_OPERATION_STRING = "KEYS OPERATION";
    private static final String ITERATE_OPERATION_STRING = "ITERATE OPERATION";
    private static final String STRING_VALUE_OPERATION_STRING = "STRING_VALUE OPERATION";
    private static final String NUMBER_VALUE_OPERATION_STRING = "NUMBER_VALUE OPERATION";
    private static final String BOOLEAN_VALUE_OPERATION_STRING = "BOOLEAN_VALUE OPERATION";
    private static final String REGEX_NUMERIC = "[0-9]*.?[0-9]*";
    private static final String REGEX_BOOLEAN = "(true|false)";

    public static final char START_OBJECT = '{';
    public static final char END_OBJECT = '}';
    public static final char START_ARRAY = '[';
    public static final char END_ARRAY = ']';
    public static final char START_STRING = '"';
    public static final char END_STRING = '"';
    public static final char KEY_DELIMITER = ':';
    public static final char VALUE_DELIMITER = ',';

    protected int characterSize;
    protected char[] characters;

    protected String key = null;
    protected Node parent = null;

    public static Node getNode(String string) {
        StringWrapper wrapping = new StringWrapper(string);
        NodeType type = wrapping.getNodeType();
        String contents = wrapping.getContents();
        return getNode(contents, type);
    }

    private static Node getNode(String string, NodeType type) {
        switch (type) {
            case OBJECT:
                return new ObjectNode(string);
            case ARRAY:
                return new ArrayNode(string);
            case STRING:
                return new StringNode(string);
            case NUMBER:
                return new NumberNode(string);
            case BOOLEAN:
                return new BooleanNode(string);
            default:
                return new NullNode();
        }
    }

    public Node get(String key) {
        throw new UnsupportedNodeOperationException(this.getClass(), GET_OPERATION_STRING);
    }

    public Node get(int index) {
        throw new UnsupportedNodeOperationException(this.getClass(), GET_OPERATION_STRING);
    }

    public int size() {
        throw new UnsupportedNodeOperationException(this.getClass(), GET_OPERATION_STRING);
    }

    public Iterable<String> getKeys() {
        throw new UnsupportedNodeOperationException(this.getClass(), KEYS_OPERATION_STRING);
    }

    public boolean isNull() {
        return false;
    }

    public String getStringValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), STRING_VALUE_OPERATION_STRING);
    }

    public double getDoubleValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), NUMBER_VALUE_OPERATION_STRING);
    }

    public int getIntegerValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), NUMBER_VALUE_OPERATION_STRING);
    }

    public int getCeilIntegerValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), NUMBER_VALUE_OPERATION_STRING);
    }

    public int getFloorIntegerValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), NUMBER_VALUE_OPERATION_STRING);
    }

    public boolean getBooleanValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), BOOLEAN_VALUE_OPERATION_STRING);
    }

    protected Character nextChar() {
        try {
            Character nextCharacter = characters[characterSize];
            characterSize++;
            return nextCharacter;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    protected String nextBody() {
        StringBuilder bodyBuilder = new StringBuilder();
        Character currentChar = nextChar();

        if (currentChar == null) {
            return null;

        } else if (currentChar.equals(START_OBJECT)) {
            int openObjects = 1;
            bodyBuilder.append(currentChar);
            while (openObjects > 0) {
                currentChar = nextChar();
                if (currentChar == null) {
                    throw new MalformedNodeException(ObjectNode.class);
                }else if (currentChar.equals(START_OBJECT)) {
                    openObjects++;
                } else if (currentChar.equals(END_OBJECT)) {
                    openObjects--;
                }
                bodyBuilder.append(currentChar);
            }
            //load comma
            currentChar = nextChar();

        } else if (currentChar.equals(START_ARRAY)) {
            int openArrays = 1;
            bodyBuilder.append(currentChar);
            while (openArrays > 0) {
                currentChar = nextChar();
                if (currentChar == null) {
                    throw new MalformedNodeException(ArrayNode.class);
                }else if (currentChar.equals(START_ARRAY)) {
                    openArrays++;
                } else if (currentChar.equals(END_ARRAY)) {
                    openArrays--;
                }
                bodyBuilder.append(currentChar);
            }
            //load comma
            currentChar = nextChar();

        } else if (currentChar.equals(START_STRING)) {
            boolean openString = true;
            bodyBuilder.append(currentChar);
            while (openString) {
                currentChar = nextChar();
                if (currentChar == null) {
                    throw new MalformedNodeException(StringNode.class);
                }else if (currentChar.equals(END_STRING)) {
                    openString = false;
                }
                bodyBuilder.append(currentChar);
            }
            //load comma
            currentChar = nextChar();

        } else {
            while (currentChar != null && !currentChar.equals(VALUE_DELIMITER)) {
                bodyBuilder.append(currentChar);
                currentChar = nextChar();
            }
            if (!bodyBuilder.toString().matches(REGEX_NUMERIC) && !bodyBuilder.toString().matches(REGEX_BOOLEAN)) {
                throw new MalformedNodeException(NumberNode.class);
            }
        }

        if (currentChar == null || currentChar.equals(VALUE_DELIMITER)) {
            return bodyBuilder.toString();
        } else {
            throw new MalformedNodeException(this.getClass());
        }
    }
}

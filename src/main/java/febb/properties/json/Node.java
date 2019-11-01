package febb.properties.json;

import febb.properties.json.exception.MalformedNodeException;
import febb.properties.json.exception.UnsupportedNodeOperationException;

import java.util.Iterator;

public abstract class Node implements Iterable<Node>{
    private static final String GET_OPERATION_STRING = "GET OPERATION";
    private static final String KEYS_OPERATION_STRING = "KEYS OPERATION";
    private static final String ITERATE_OPERATION_STRING = "ITERATE OPERATION";
    private static final String STRING_VALUE_OPERATION_STRING = "STRING_VALUE OPERATION";
    private static final String NUMBER_VALUE_OPERATION_STRING = "NUMBER_VALUE OPERATION";

    public static final char START_OBJECT = '{';
    public static final char END_OBJECT = '}';
    public static final char START_ARRAY = '[';
    public static final char END_ARRAY = ']';
    public static final char START_STRING = '"';
    public static final char END_STRING = '"';
    public static final char KEY_DELIMITER = ':';
    public static final char VALUE_DELIMITER = ',';

    protected int size;
    protected char[] characters;

    protected String key = null;
    protected Node parent = null;

    public Node get(String key) {
        throw new UnsupportedNodeOperationException(this.getClass(), GET_OPERATION_STRING);
    }

    public Node get(int key) {
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

    public double getNumberValue() {
        throw new UnsupportedNodeOperationException(this.getClass(), NUMBER_VALUE_OPERATION_STRING);
    }

    @Override
    public Iterator<Node> iterator() {
        throw new UnsupportedNodeOperationException(this.getClass(), ITERATE_OPERATION_STRING);
    }

    protected static Node getNode(String string) {
        StringWrapper wrapping = new StringWrapper(string);
        NodeType type = wrapping.getNodeType();
        String contents = wrapping.getContents();
        return getNode(string, type);
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
            default:
                return new NullNode();
        }
    }

    protected char nextChar() {
        size++;
        try {
            return characters[size];
        } catch (IndexOutOfBoundsException exception) {
            throw new MalformedNodeException(this.getClass());
        }
    }


    protected String nextBody() {
        StringBuilder bodyBuilder = new StringBuilder();
        char currentChar = nextChar();

        switch (currentChar) {
            case START_OBJECT:
                int openObjects = 1;
                bodyBuilder.append(currentChar);
                while (openObjects > 0) {
                    currentChar = nextChar();
                    if (currentChar == START_OBJECT) {
                        openObjects++;
                    } else if (currentChar == END_OBJECT) {
                        openObjects--;
                    }
                    bodyBuilder.append(currentChar);
                }
                //load comma
                currentChar = nextChar();

            case START_ARRAY:
                int openArrays = 1;
                bodyBuilder.append(currentChar);
                while(openArrays > 0) {
                    currentChar = nextChar();
                    if (currentChar == START_ARRAY) {
                        openArrays++;
                    } else if (currentChar == END_ARRAY) {
                        openArrays--;
                    }
                    bodyBuilder.append(currentChar);
                }
                //load comma
                currentChar = nextChar();

            case START_STRING:
                boolean openString = true;
                bodyBuilder.append(currentChar);
                while (openString) {
                    currentChar = nextChar();
                    if (currentChar == END_STRING) {
                        openString = false;
                    }
                    bodyBuilder.append(currentChar);
                }
                //load comma
                currentChar = nextChar();

            default:
                while (currentChar != VALUE_DELIMITER) {
                    bodyBuilder.append(currentChar);
                    currentChar = nextChar();
                }
        }

        //TODO: validate this IDE warning at runtime
        if(currentChar != VALUE_DELIMITER) {
            throw new MalformedNodeException(this.getClass());
        } else {
            return bodyBuilder.toString();
        }
    }
}

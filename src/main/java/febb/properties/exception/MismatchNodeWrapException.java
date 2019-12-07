package febb.properties.exception;

public class MismatchNodeWrapException extends RuntimeException {
    public MismatchNodeWrapException(String startWrap, String endWrap) {
        super(startWrap + " does not match " + endWrap);
    }
}

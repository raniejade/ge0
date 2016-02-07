package io.polymorphicpanda.ge0.zero;

/**
 * @author Ranie Jade Ramiso
 */
public class ZeroRuntimeException extends RuntimeException {
    public ZeroRuntimeException(String message) {
        super(message);
    }

    public ZeroRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

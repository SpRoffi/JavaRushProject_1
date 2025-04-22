package exceptions;

public class ShiftException extends RuntimeException {
    public ShiftException(String message) {
        super((message));
    }
}

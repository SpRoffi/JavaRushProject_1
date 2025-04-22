package exceptions;

public class CommandsException extends RuntimeException {
    public CommandsException(String message) {
        super(message);
    }
}

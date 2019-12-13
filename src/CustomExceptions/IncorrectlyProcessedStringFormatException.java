package CustomExceptions;

public class IncorrectlyProcessedStringFormatException extends RuntimeException {

    public IncorrectlyProcessedStringFormatException(String message) {
        super(message);
    }

    public IncorrectlyProcessedStringFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectlyProcessedStringFormatException(Throwable cause) {
        super(cause);
    }
}

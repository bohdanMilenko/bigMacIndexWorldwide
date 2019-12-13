package CustomExceptions;

public class IncorrectCSVFormatException extends RuntimeException {

    public IncorrectCSVFormatException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public IncorrectCSVFormatException(String errorMessage) {
        super(errorMessage);
    }
}

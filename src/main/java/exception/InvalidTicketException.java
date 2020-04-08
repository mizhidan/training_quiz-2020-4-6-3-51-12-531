package exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException() {

    }

    public InvalidTicketException(String message) {
        super(message);
        System.exit(0);
    }

    public InvalidTicketException(String message, Throwable cause) {
        super(message, cause);
        System.exit(0);
    }
}

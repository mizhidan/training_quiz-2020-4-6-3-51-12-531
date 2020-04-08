package exception;

public class ParkingLotAllFullException extends RuntimeException{
    public ParkingLotAllFullException() {

    }
    public ParkingLotAllFullException(String message) {
        super(message);
        System.exit(0);
    }

    public ParkingLotAllFullException(String message, Throwable cause) {
        super(message, cause);
        System.exit(0);
    }
}

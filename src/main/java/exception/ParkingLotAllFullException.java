package exception;

public class ParkingLotAllFullException extends RuntimeException{
    public ParkingLotAllFullException() {

    }
    public ParkingLotAllFullException(String message) {
        super(message);
    }

    public ParkingLotAllFullException(String message, Throwable cause) {
        super(message, cause);
    }
}

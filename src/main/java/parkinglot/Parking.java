package parkinglot;

public class Parking {
    private int id;
    private String carNumber;
    private int parkingTime;

    public Parking(int id, String carNumber) {
        this.id = id;
        this.carNumber = carNumber;
    }

    public Parking() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }
}

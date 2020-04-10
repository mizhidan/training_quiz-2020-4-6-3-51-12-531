package parkinglot;

public class ParkingInfo {
    private int parkingTime;
    private int parkingCost;

    public ParkingInfo() {

    }

    public ParkingInfo(int parkingTime, int parkingCost) {
        this.parkingTime = parkingTime;
        this.parkingCost = parkingCost;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public int getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(int parkingCost) {
        this.parkingCost = parkingCost;
    }
}

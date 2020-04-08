package parkinglot;

import Database.DbUtil;
import exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;


public class ParkingLot {
    List<Parking> parkingList = new ArrayList<>();
    private int maxCount;
    DbUtil dbUtil;
    public ParkingLot() {
       //TODO: 实现数据库的读取功能
    }

    public boolean addCar(String carNumber) {
        if(maxCount == parkingList.size()) {
            throw new ParkingLotFullException();
        }
        int id = parkingList.size() + 1;
        Parking parking = new Parking(id,carNumber);
        return parkingList.add(parking);
    }


    public void setParkingList() {
        this.parkingList = new ArrayList<>(maxCount);
    }

    public List<Parking> getParkingList() {
        return parkingList;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount = Integer.parseInt(maxCount);
    }
}

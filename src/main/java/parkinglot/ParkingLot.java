package parkinglot;

import Database.DbUtil;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;


public class ParkingLot {
    List<Parking> parkingList = new ArrayList<>();
    private int maxCount;
    private String parkingLotId;
    DbUtil dbUtil = new DbUtil();
    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
       //TODO: 实现数据库的读取功能
    }


    public String addCar(String carNumber) {
        if(maxCount == parkingList.size()) {
            throw new ParkingLotFullException();
        }
        int id = parkingList.size() + 1;
        Parking parking = new Parking(id,carNumber);
        StringBuilder ticket = new StringBuilder();
        ticket.append(parkingLotId + "," + id + "," + carNumber);
        return ticket.toString();
    }

    public String getCar(String ticket) {
        int id = Integer.parseInt(ticket.split(",")[0]);
        String carName = ticket.split(",")[1];
        if (carName.equals(parkingList.get(id))) {
            return carName;
        } else {
            throw new InvalidTicketException
                    ("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！ ");
        }
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

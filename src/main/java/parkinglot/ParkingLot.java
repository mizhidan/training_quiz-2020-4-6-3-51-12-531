package parkinglot;

import Database.ParkingDAO;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.List;


public class ParkingLot {
    List<Parking> parkingList;
    private int maxCount;
    private String parkingLotId;
    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
       ParkingDAO parkingDAO = new ParkingDAO();
       parkingList = parkingDAO.ListParking(parkingLotId);
       maxCount = parkingList.size();
    }


    public String addCar(String carNumber) {
        int index;
        StringBuilder ticket = new StringBuilder();
        for (index = 0; index < parkingList.size(); ++index) {
            if(parkingList.get(index).getCarNumber().equals("''")) {
                Parking parking = new Parking(index + 1,carNumber);
                parkingList.get(index).setCarNumber(carNumber);
                ticket.append(parkingLotId + "," + (index + 1) + "," + carNumber);
                break;
            }
            if(index == parkingList.size() - 1) {
                throw new ParkingLotFullException();
            }
        }
        return ticket.toString();
    }

    public String getCar(String ticket) {
        int id = Integer.parseInt(ticket.split(",")[0]);
        String carName = ticket.split(",")[1];
        if(id > parkingList.size() | id < 0) {
            throw new InvalidTicketException
                    ("停车券无效");
        }
        if (carName.equals(parkingList.get(id - 1).getCarNumber())) {
            parkingList.get(id - 1).setCarNumber("''");
            return carName;
        } else {
            throw new InvalidTicketException
                    ("停车券无效");
        }
    }

    public void init(String maxCount) {
        ParkingDAO parkingDAO = new ParkingDAO();
        parkingDAO.initDB(Integer.parseInt(maxCount),parkingLotId);
        this.parkingList = parkingDAO.ListParking(parkingLotId);
        setMaxCount(parkingList.size());
    }

    public void update() {
        ParkingDAO parkingDAO = new ParkingDAO();
        parkingDAO.updateDB(maxCount,parkingLotId,parkingList);
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

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}

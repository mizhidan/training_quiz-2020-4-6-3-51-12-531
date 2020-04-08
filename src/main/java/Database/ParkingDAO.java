package Database;

import parkinglot.Parking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingDAO {
    public ParkingDAO() {

    }

    public List<Parking> ListParking(String parkingLotId) {
        List<Parking> parkings = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/parkinglot?useUnicode=true&characterEncoding=utf-8" +
                                    "&serverTimezone=Hongkong",
                            "root", "password");
            String sql = "select * from " + parkingLotId + "_parkinglot " + "order by id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Parking parking = new Parking();
                int id = rs.getInt(1);
                String name = rs.getString(2);

                parking.setId(id);
                parking.setCarNumber(name);
                parkings.add(parking);
            }
            ps.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return parkings;
    }

    public void initDB(int maxCount, String parkingLotId) {
        int index;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/parkinglot?useUnicode=true&characterEncoding=utf-8" +
                                    "&serverTimezone=Hongkong",
                            "root", "password");
            String sql="TRUNCATE TABLE " + parkingLotId + "_parkinglot";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/parkinglot?useUnicode=true&characterEncoding=utf-8" +
                                    "&serverTimezone=Hongkong",
                            "root", "password");
            String sql="INSERT INTO " + parkingLotId + "_parkinglot" + "(id,carnumber) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (index = 1; index <= maxCount; ++index) {
                ps.setInt(1,index);
                ps.setString(2,"''");
                ps.executeUpdate();
            }

            ps.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDB(int maxCount, String parkingLotId, List<Parking> parkingList) {
        int index;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://127.0.0.1:3306/parkinglot?useUnicode=true&characterEncoding=utf-8" +
                                    "&serverTimezone=Hongkong",
                            "root", "password");
            String sql = "Update " + parkingLotId + "_parkinglot set carnumber =? where id =? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (index = 1; index <= maxCount; ++index) {
                ps.setInt(2,index);
                ps.setString(1,parkingList.get(index - 1).getCarNumber());
                ps.execute();
            }
            ps.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public void updateRoom (Parking parking) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mec_hotel?useUnicode=true&characterEncoding=utf-8",
//                    "root", "root");
//
//
//            PreparedStatement Statement = connection.prepareStatement(sqlString);
//            Statement.setString(1,usedRoom.getUserId());
//            Statement.setString(2, usedRoom.getUserName());
//            Statement.setInt(3, usedRoom.getRentTypeId());
//            Statement.setBoolean(4, usedRoom.isRoomState());
//            Statement.setString(5, usedRoom.getBeginTime());
//            Statement.setInt(6, Integer.valueOf(usedRoom.getRoomId()));
//            Statement.execute();
//            Statement.close();
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

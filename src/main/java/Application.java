import Database.ParkingDAO;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;
import parkinglot.Parking;
import parkinglot.ParkingInfo;
import parkinglot.ParkingLot;

import java.util.Scanner;

public class Application {

    private static ParkingLot aParkingLot = new ParkingLot("A");
    private static ParkingLot bParkingLot = new ParkingLot("B");
    private static ParkingDAO parkingDAO = new ParkingDAO();

    public static void main(String[] args) {
        operateParking();
    }

    public static void operateParking() {
        while (true) {
            System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
            Scanner printItem = new Scanner(System.in);
            String choice = printItem.next();
            if (choice.equals("4")) {
                aParkingLot.update();
                bParkingLot.update();
                System.out.println("系统已退出");
                break;
            }
            handle(choice);
        }
    }

    private static void handle(String choice) {
        Scanner scanner = new Scanner(System.in);
        if (choice.equals("1")) {
            System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
            String initInfo = scanner.next();
            init(initInfo);
        } else if (choice.equals("2")) {
            System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
            String carInfo = scanner.next();
            String ticket = park(carInfo);
            String[] ticketDetails = ticket.split(",");
            System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
        } else if (choice.equals("3")) {
            System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
            String ticket = scanner.next();
            String car = fetch(ticket);
            ParkingInfo parkingInfo = new ParkingInfo();
            String parkingLotNum = ticket.trim().split(",")[0];
            if (parkingLotNum.equals("A")) {
                parkingInfo = aParkingLot.getParkingInfo(ticket);
            } else if (parkingLotNum.equals("B")) {
                parkingInfo = bParkingLot.getParkingInfo(ticket);
            }
            System.out.format("已为您取到车牌号为%s的车辆，您的停车时间为%d小时，停车费用为%d元\n很高兴为您服务，祝您生活愉快!\n",
                    car, parkingInfo.getParkingTime(), parkingInfo.getParkingCost());
        }
    }

    public static void init(String initInfo) {
        aParkingLot.init(initInfo.trim().split(",")[0].split(":")[1]);
        bParkingLot.init(initInfo.trim().split(",")[1].split(":")[1]);
    }

    public static String park(String carNumber) {
        String ticket = "";
        int aCapacity = aParkingLot.calcLastCapacity();
        int bCapacity = bParkingLot.calcLastCapacity();
        if (aCapacity == 0 && bCapacity == 0) {
            throw new ParkingLotFullException("非常抱歉，由于车位已满，暂时无法为您停车！");
        } else if (bCapacity > aCapacity) {
            ticket = bParkingLot.addCar(carNumber);
        } else {
            ticket = aParkingLot.addCar(carNumber);
        }
//        try {
//            ticket = aParkingLot.addCar(carNumber);
//        } catch (ParkingLotFullException e) {
//            try {
//                ticket = bParkingLot.addCar(carNumber);
//            } catch (ParkingLotFullException e1) {
//                throw new ParkingLotFullException("非常抱歉，由于车位已满，暂时无法为您停车！");
//            }
//        }
        return ticket;
    }

    public static String fetch(String ticket) {
        String parkingLotNum = ticket.trim().split(",")[0];
        StringBuilder carInfo = new StringBuilder();
        carInfo.append(ticket.trim().split(",")[1] + "," + ticket.trim().split(",")[2]);
        if (parkingLotNum.equals("A")) {
            return aParkingLot.getCar(carInfo.toString());
        } else if (parkingLotNum.equals("B")) {
            return bParkingLot.getCar(carInfo.toString());
        } else {
            throw new InvalidTicketException
                    ("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！ ");
        }
    }

}

import exception.InvalidTicketException;
import exception.ParkingLotAllFullException;
import exception.ParkingLotFullException;
import parkinglot.ParkingLot;

import java.util.Scanner;

public class Application {

  private static ParkingLot aParkingLot = new ParkingLot("a");
  private static ParkingLot bParkingLot = new ParkingLot("b");
  public static void main(String[] args) {
    operateParking();
  }

  public static void operateParking() {
    while (true) {
      System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
      Scanner printItem = new Scanner(System.in);
      String choice = printItem.next();
      if (choice.equals("4")) {
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
    }
    else if (choice.equals("2")) {
      System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
      String carInfo = scanner.next();
      String ticket = park(carInfo);
      String[] ticketDetails = ticket.split(",");
      System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
    }
    else if (choice.equals("3")) {
      System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
      String ticket = scanner.next();
      String car = fetch(ticket);
      System.out.format("已为您取到车牌号为%s的车辆，很高兴为您服务，祝您生活愉快!\n", car);
    }
  }

  public static void init(String initInfo) {
    aParkingLot.setMaxCount(initInfo.trim().split(",")[0].split(":")[1]);
    aParkingLot.setParkingList();
    bParkingLot.setMaxCount(initInfo.trim().split(",")[0].split(":")[1]);
    bParkingLot.setParkingList();
  }

  public static String park(String carNumber) {
    String ticket = "";
    try {
      ticket = aParkingLot.addCar(carNumber);
    } catch (ParkingLotFullException e) {
      try {
        ticket = bParkingLot.addCar(carNumber);
      } catch (ParkingLotFullException e1) {
        throw new ParkingLotAllFullException("非常抱歉，由于车位已满，暂时无法为您停车！");
      }
    }
    return ticket;
  }

  public static String fetch(String ticket) {
    String parkingLotNum = ticket.trim().split(",")[1];
    StringBuilder carInfo = new StringBuilder();
    carInfo.append(ticket.trim().split(",")[1] + ticket.trim().split(",")[2]);
    if (parkingLotNum.equals("a")) {
      return aParkingLot.getCar(carInfo.toString());
    } else if (parkingLotNum.equals("b")) {
      return bParkingLot.getCar(carInfo.toString());
    } else {
      throw new InvalidTicketException
              ("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！ ");
    }
  }

}

package operation;
import warehouse.Room;
import warehouse.Customer;
import java.util.LinkedList;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author administrator
 * @version Java 17
 */
public class DataBase implements RoomAdd, RoomList{

	// 定义字段和相关的加载、存储字段的类
	// 定义数据库存储的方式
	public static String path = "DatabaseOfInn";
	private static Map<String, String> CuIDtoRoomID = new HashMap<>();	// 入住顾客的房间列表，键-值: 顾客id - 房间id
	private static LinkedList<Customer> customers = new LinkedList<>();	// 顾客列表, 存储顾客的姓名、身份证号、顾客的电话，如果要查找顾客当前住的房间，访问CuIDtoRoomID
	private static Map<String, Customer> customersID = new HashMap<>();	// 顾客列表，存储顾客的身份证号、和顾客的全部信息构成的键值对
	private static LinkedList<Room> rooms = new LinkedList<> ();		// 旅店房间列表, 存储房号、房型、价格信息
	private static LinkedList<String> roomsbyRoomNUM = new LinkedList<>();	// 旅店房间列表, 只存储房号
	private static LinkedList<CustomerEvent> eventList = new LinkedList<> ();	// 记录顾客入住的事件
	private static Map<String, Room> roomsNUM = new HashMap<>();	// 房间号列表
	private static Map<String, String> statesOfRooms = new HashMap<> ();	// 房间号 - 房间状态
	private static LinkedList<Room> abandonedRooms = new LinkedList<> ();
	private static DataBase database = null;
	private DataBase() {}
	public static DataBase newDataBase() {return DataBase.database;}
	@Override public void roomAdd(String roomNUM, String roomType, double price) {
		if (DataBase.roomsbyRoomNUM.contains(roomNUM)) {
			System.out.println("输入的房号已存在");
		}
		else {
			Room r1 = Room.newRoom(roomNUM, roomType, price);
			DataBase.roomsbyRoomNUM.add(roomNUM);
			DataBase.rooms.add(r1);
			DataBase.roomsNUM.put(roomNUM, r1);
			DataBase.statesOfRooms.put(roomNUM, RoomState.空置.toString());
		}
	}
	@ Override public void roomRemove(String roomNUM) {
		if (DataBase.roomsbyRoomNUM.contains(roomNUM)) {
			if (DataBase.statesOfRooms.get(roomNUM).equals(RoomState.入住.toString())) {
				System.out.println("房间正在使用，无法删除房间");
			}
			else {
				Room tempr = DataBase.roomsNUM.get(roomNUM);
				DataBase.roomsNUM.remove(roomNUM);
				DataBase.rooms.remove(tempr);
				DataBase.roomsbyRoomNUM.remove(roomNUM);
				DataBase.abandonedRooms.add(tempr);
			}
		}
	}
	@Override public ArrayList<String> listrooms() {
		ArrayList<String> arrStr = new ArrayList<>();
		if (DataBase.rooms.get(0) == null) {
			return arrStr;
		}
		ArrayList<Room> arr = new ArrayList<>();
		for (Room r : DataBase.rooms) {
			arr.add(r);
			System.out.println(r);
		}
		// 给列表 ArrayList<Room> arr 排个序
		for (int i=0; i< arr.size(); i++) {
			for (int j=0; j < arr.size() - 1 - i; j++) {
				if (arr.get(j).compareTo(arr.get(j+1)) == 1) {
					Room tempRo = arr.get(j);
					arr.set(j, arr.get(j+1));
					arr.set(j+1, tempRo);
				}
			}
		}
		// 把房间信息转换成字符串后，添加到arrStr中
		for (Room r2 : arr) {
			arrStr.add(r2.toString());
		}
		return arrStr;
	}
	public void custMoveIn(String customerID, String phone, String name, String roomNUM) {
		Customer c = Customer.newCustomer(name, customerID, phone);
		// 如果顾客列表中没有这个顾客，将之添入顾客列表;
		if (! customers.contains(c)) {
			customers.add(c);
			customersID.put(customerID, c);
			System.out.println("客户已添加入客户列表");
		}
		// 如果房号不在 roomsbyRoomNUM 中，返回提示房号不存在，如果房间状态清单 statesOfRooms 显示定的房间不是空置，提示房间未空置
		if (roomsbyRoomNUM.contains(roomNUM)) {
			if (DataBase.statesOfRooms.get(roomNUM).equals(RoomState.空置.toString())) {

				CustomerMoveIn cmi = CustomerMoveIn.cmovein(customerID, roomNUM);
				DataBase.statesOfRooms.replace(roomNUM, RoomState.入住.toString());
				DataBase.eventList.add((CustomerEvent) cmi);
				DataBase.CuIDtoRoomID.put(customerID, roomNUM);
			}
			else {System.out.println("房间未空置");} 
		}
		else {System.out.println("输入的房间号不存在. ");}
	}
	public void custMoveOut(String customerID, String roomNUM) {
		if (DataBase.CuIDtoRoomID.containsKey(customerID) && DataBase.CuIDtoRoomID.get(customerID).equals(roomNUM)) {
			CustomerMoveOut cmo = CustomerMoveOut.cmoveOut(customerID, roomNUM);
			DataBase.eventList.add(cmo);
			DataBase.CuIDtoRoomID.remove(customerID);
			DataBase.statesOfRooms.replace(roomNUM, RoomState.打扫中.toString());
		}
		else {System.out.println("查无此入住信息，请重新输入");}
	}
	// 将内存中的列表储存到永久存储中
	public void dataWrite() {
		ObjectOutputStream fw = null;
		try {
			fw = new ObjectOutputStream(new FileOutputStream(path));
			fw.writeObject(DataBase.rooms);
			fw.writeObject(DataBase.customers);
			fw.writeObject(DataBase.roomsNUM);
			fw.writeObject(DataBase.CuIDtoRoomID);
			fw.writeObject(DataBase.statesOfRooms);
			fw.writeObject(DataBase.eventList);
			fw.writeObject(DataBase.customersID);
			fw.writeObject(DataBase.roomsbyRoomNUM);
			fw.writeObject(DataBase.abandonedRooms);
		} catch (IOException e) {
			// 捕获写类数据时的写入异常
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// 捕获 fw 关闭可能会带来的异常
					e.printStackTrace();
				}
			}
		}
	}
	public void dataLoad() {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(path));
			if ((DataBase.rooms = (LinkedList<Room>) ois.readObject()) != null) {

				DataBase.customers = (LinkedList<Customer>) ois.readObject();
				DataBase.roomsNUM = (Map<String, Room>) ois.readObject();
				DataBase.CuIDtoRoomID = (Map<String, String>) ois.readObject();
				DataBase.statesOfRooms = (Map<String, String>) ois.readObject();
				DataBase.eventList = (LinkedList<CustomerEvent>) ois.readObject();
				DataBase.customersID = (Map<String, Customer>) ois.readObject();
				DataBase.roomsbyRoomNUM = (LinkedList<String>) ois.readObject();
				DataBase.abandonedRooms = (LinkedList<Room>) ois.readObject();
			}
			else {DataBase.rooms = new LinkedList<Room>();}		// 在 if 判断句中已重写加载好的 rooms, 如果重写不成功，在这里就重置rooms 为新的空表;
		} catch (FileNotFoundException e) {
			// 捕获文件异常
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// 捕获类异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获 io 异常
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// 捕获 ois 关闭时可能的异常
					e.printStackTrace();
				}
			}
		}

	}
}

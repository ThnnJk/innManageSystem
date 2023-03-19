package warehouse;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author administrator
 * @version Java 17
 */
public class Room implements Serializable, Comparable<Room> {
	/**声明一个序列号，方便使用 ObjectOutputStream 向永久存储器中写入 Room 对象
	 * @serialField serialVertionUID
	 */
	private static final long serialVersionUID = 1L;
	
	String roomNum;
	RoomType roomType;
	double price;

	
	private Room() {
		this("00000", "单人间", 0);
	}
	private Room(String roomNum, String roomType, double price) {
		this.roomNum = roomNum;
		this.roomType = RoomType.toRoomType(roomType);
		this.price = price;
	}
	
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String[] listRoomType() {
		return RoomType.listRoomType();
	}
	
	@Override public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", price=" + price + "]";
	}

	// 定义房间的种类: 标准间, 单人间, 豪华间
	enum RoomType {
		单人间, 标准间, 豪华间;
		static String[] listRoomType() {
			ArrayList<String> arr = new ArrayList<>();
			String[] s = new String[3];
			for (RoomType r : RoomType.values()) {
				arr.add(r.toString());
			}
			for (int i = 0; i < arr.size(); i++) {
				s[i] = arr.get(i);
			}
			return s;
		}
		static RoomType toRoomType(String s) {
			if (s.equals(RoomType.单人间.toString())) {return RoomType.单人间;}
			else if (s.equals(RoomType.豪华间.toString())) {return RoomType.豪华间;}
			else if (s.equals(RoomType.标准间.toString())) {return RoomType.标准间;}
			else {return RoomType.单人间;}
		}
	}
	@Override public int compareTo(Room o) {
		// 根据房间价格大小定义Room类的顺序
		if (this.getPrice() < o.getPrice()) {return -1;}
		else if (this.getPrice() == o.getPrice()) {return 0;}
		else {return 1;}
	}
	@Override public boolean equals(Object o) {
		boolean asser = false;
		Room troom = (Room) o;
		if (troom.getRoomNum() == this.getRoomNum()) {asser = true;}
		return asser;
	}
	public static Room newRoom(String roomNUM, String roomType, double price) {
		return new Room(roomNUM, roomType, price);
	}
}

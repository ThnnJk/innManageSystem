package operation;

import java.util.Date;

// 办理入住手续
// 记录一次入住事件：记录顾客身份证(customerID字段), 记录入住房间的房号(roomNUM ), 记录入住日期(moveinTime)
// state 字段用以区分事件: true 表示顾客入住， false 表示顾客退房；

public class CustomerMoveIn extends CustomerEvent {
	String customerID;
	Date moveinTime;
	String roomNUM;
	boolean state;
	private CustomerMoveIn(String customerID, String roomNUM, Date moveinTime) {
		super(customerID, moveinTime, roomNUM, true);
		
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Date getMoveinTime() {
		return moveinTime;
	}

	public void setMoveinTime(Date moveinTime) {
		this.moveinTime = moveinTime;
	}

	public boolean isState() {
		return state;
	}

	public String getRoomNUM() {
		return roomNUM;
	}

	public void setRoomNUM(String roomNUM) {
		this.roomNUM = roomNUM;
	}
	
	public static CustomerMoveIn cmovein(String customerID, String roomNUM) {
		Date moveinTime = new Date();
		return new CustomerMoveIn(customerID, roomNUM, moveinTime);
	}
}

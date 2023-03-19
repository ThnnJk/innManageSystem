package operation;

import java.util.Date;

//记录一次退房事件：记录顾客身份证(customerID字段), 记录退房房间的房号(roomNUM ), 记录退房日期(moveoutTime)
public class CustomerMoveOut extends CustomerEvent {
	String customerID;
	Date moveoutTime;
	String roomNUM;
	boolean state;
	private CustomerMoveOut(String customerID, String roomNUM, Date moveoutTime) {
		super(customerID, moveoutTime, roomNUM, false);
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Date getMoveoutTime() {
		return moveoutTime;
	}

	public void setMoveoutTime(Date moveoutTime) {
		this.moveoutTime = moveoutTime;
	}

	public String getRoomNUM() {
		return roomNUM;
	}

	public void setRoomNUM(String roomNUM) {
		this.roomNUM = roomNUM;
	}
	
	public boolean isState() {
		return state;
	}

	public static CustomerMoveOut cmoveOut(String customerID, String roomNUM) {
		Date moveoutTime = new Date();
		return new CustomerMoveOut(customerID, roomNUM, moveoutTime);
	}
}

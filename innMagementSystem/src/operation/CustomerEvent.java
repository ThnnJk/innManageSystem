package operation;

import java.util.Date;

public class CustomerEvent {
	String customerID;
	Date eventTime;
	String roomNUM;
	boolean state;

	
	public CustomerEvent(String customerID, Date eventTime, String roomNUM, boolean state) {
		this.customerID = customerID;
		this.eventTime = eventTime;
		this.roomNUM = roomNUM;
		this.state = state;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
}

package operation;
import warehouse.Room;
public interface RoomAdd {
	abstract void roomAdd(String roomNUM, String roomType, double price);
	abstract void roomRemove(String roomNUM);
}

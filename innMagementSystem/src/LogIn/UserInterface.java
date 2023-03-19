package LogIn;
import java.util.ArrayList;
import java.util.Scanner;
import operation.*;
import warehouse.*;
public class UserInterface implements Runnable {
	@Override public void run() {
		DataBase db = DataBase.newDataBase();
		boolean bs = true;
		while (bs) {
			Scanner syin = new Scanner(System.in);
			System.out.println("    1、查看房间");
			System.out.println("    2、增加房间");
			System.out.println("    3、删除房间");
			System.out.println("    4、办理入住");
			System.out.println("    5、办理退房");
			System.out.println("    6、房间排序");
			System.out.println("    7、保存房间");
			System.out.println("    8、加载房间");
			System.out.println("    9、退出系统");
			String ch = syin.next();
			syin.close();
			switch (ch) {
			case "1": 		// 查看房间
				ArrayList<String> listOfRooms = db.listrooms();
				for (String ro : listOfRooms) {
					System.out.println(ro);
				}
				continue;
			case "2": 		// 增加房间
				boolean b2 = true;
				boolean inputState = false;		// 写一个表示输入成功与否的状态, 待会决定是否增加新房间的时候会用上这个
				String roomnum = new String();
				String roomtype = new String();
				double price = 0;
				while (b2) {
					Scanner s1 = new Scanner(System.in);
					boolean bRoomin = false;
					while (bRoomin != true) {
						System.out.println("    请输入房号, 按 x 键退出");
						String temp1 = new String();
						if ((temp1 = s1.next()) == "x") {
							b2 = true;
							s1.close();
							continue;
						}

						else {
							roomnum = temp1;
							bRoomin = true;
						}
					}
					//
					System.out.println("    请选择房型: 1. 标准间, 2. 单人间, 3. 豪华间, 按 x 键退出");
					String temp2 = new String();
					if ((temp2 = s1.next()) == "x") {
						b2 = true;
						s1.close();
						continue;
					}
					else if (temp2 == "1") {
						roomtype = "标准间";
					}
					else if (temp2 == "2") {
						roomtype = "单人间";
					}
					else if (temp2 == "3")
					}
				
				System.out.println("    3、删除房间");
				continue;
			case "3": break;
			case "4": break;
			case "5": break;
			case "6": break;
			case "7": break;
			case "8": break;
			case "9": break;
			default: 
			}
		}
	}
}

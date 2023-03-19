package LogIn;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Supplier;

public class LogIn {
	// 
	private static Properties accounts = new Properties();
	private LogIn(String path) {
		FileReader fw = null;
		
		try {
			fw = new FileReader(path);
			if (accounts != null) {
				accounts.load(fw);
			}
		} catch (IOException e) {
			// 捕获文件对象读取时的异常
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				// 捕获文件对象关闭时的异常
				e.printStackTrace();
			}
		}
		
		
	}
	
	public LogIn loadingAccountsInfo(String path) {
		return login;
	}
	public void logout(); {}
}

package warehouse;

import java.io.Serializable;

/**
 * @author Administrator
 * @version Java 17
 */
public class Customer implements Serializable {
	/**
	 * @serialField serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	// 其他字段
	String name;
	String id;
	String phone;
	
	private Customer() {
		this("张三", "00000000000", "123-2345-22341");
		
	}
	private Customer(String name, String id, String phone) {
		this.name = name;
		this.id = id;
		this.phone = phone;
	}

	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getId() {
		return id;
	}
	void setId(String id) {
		this.id = id;
	}
	String getPhone() {
		return phone;
	}
	void setPhone(String phone) {
		this.phone = phone;
	}

	@Override public String toString() {
		return "Customer: { name=" + name + "; id=" + id + "; phone=" + phone;
	}
	@Override public boolean equals(Object o) {
		Customer custo = (Customer) o;
		return this.getId().equals(custo.getId());
	}
	public static Customer newCustomer(String name, String id, String phone) {
		return new Customer(name, id, phone);
	}
}

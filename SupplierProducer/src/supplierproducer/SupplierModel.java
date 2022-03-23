package supplierproducer;

public class SupplierModel {

	private int id;
	private String name;
	private String address;
	private String email;
	private String uname;
	private String password;

	public SupplierModel(int id, String name, String address, String email, String uname) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.uname = uname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

package medicineproducer;

public class MedicineModel {

	private int id;
	private String name;
	private float price;
	private int qty;

	public MedicineModel(int id, String name, float price, int qty) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public int getQTY() {
		return qty;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setQTY(int qty) {
		this.qty = qty;
	}

}

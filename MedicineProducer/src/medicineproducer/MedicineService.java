package medicineproducer;

import java.util.ArrayList;

public interface MedicineService {

	public void insertMedicine(String name, float price, int qty);

	public void removeMedicine(int medicineID);

	public void updateMedicine(int medicineID, String name, float price, int qty);

	public ArrayList<MedicineModel> viewMedicines();

	public MedicineModel getMedicineDetails(int medicineID);
}

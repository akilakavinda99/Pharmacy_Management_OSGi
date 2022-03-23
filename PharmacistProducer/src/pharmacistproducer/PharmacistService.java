package pharmacistproducer;

import java.util.ArrayList;

public interface PharmacistService {

	public void addPharmacist(String name, int phoneNumber, String email, String nic, String address, String password);

	public void removePharmacist(int pharmacistID);

	public void updatePharmacist(int pharmacistID, String name, int phoneNumber, String email, String nic,
			String address);

	public void updatePharmacistPass(int pharmacistID, String password);

	public ArrayList<PharmacistModel> viewPharmacists();

	public PharmacistModel getPharmacistDetails(int pharmacistID);

}

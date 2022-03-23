package supplierproducer;

import java.util.ArrayList;

public interface SupplierService {

	public void addSupplier(String name, String address, String email, String uname, String password);

	public void removeSupplier(int supplierID);

	public void updateSupplier(int supplierID, String name, String address, String email, String uname);

	public void updateSupplierPass(int supplierID, String pass);

	public ArrayList<SupplierModel> viewSuppliers();

	public SupplierModel getSupplierDetails(int supplierID);

}

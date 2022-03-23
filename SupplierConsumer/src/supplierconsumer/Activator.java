package supplierconsumer;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import supplierproducer.SupplierModel;
import supplierproducer.SupplierService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Supplier Consumer started");
		serviceReference = bundleContext.getServiceReference(SupplierService.class.getName());

		if (serviceReference != null) {
			SupplierService supplierService = (SupplierService) bundleContext.getService(serviceReference);
			supplierMenu(supplierService);
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Supplier Consumer stopped");
		bundleContext.ungetService(serviceReference);
	}

	private void supplierMenu(SupplierService supplierService) {
		int select = 0;
		String name;
		String address;
		String email;
		String uname;
		String password;

		Scanner sc = new Scanner(System.in);

		while (select != -1) {
			System.out.println("\n================================");
			System.out.println("Supplier management:");
			System.out.println("\t1) Add new supplier");
			System.out.println("\t2) Remove supplier");
			System.out.println("\t3) Update supplier");
			System.out.println("\t4) View all suppliers");

			System.out.print("\nPlease select a option: ");
			select = Integer.parseInt(sc.nextLine().trim());

			if (select == 1) {
				System.out.print("Name: ");
				name = sc.nextLine();

				System.out.print("Address: ");
				address = sc.nextLine();

				System.out.print("Email: ");
				email = sc.nextLine();

				System.out.print("Username: ");
				uname = sc.nextLine();

				password = "pass123";

				supplierService.addSupplier(name, address, email, uname, password);

			} else if (select == 2) {
				ArrayList<SupplierModel> supList = supplierService.viewSuppliers();

				System.out.println("\n******** Registered Suppliers ********");
				System.out.println("ID \t Name");

				for (SupplierModel sup : supList) {
					System.out.println(sup.getId() + " \t " + sup.getName());
				}

				System.out.print("\nEnter ID of the supplier you wants to remove: ");

				int removeSelect = Integer.parseInt(sc.nextLine().trim());

				supplierService.removeSupplier(removeSelect);

			} else if (select == 3) {
				ArrayList<SupplierModel> supList = supplierService.viewSuppliers();

				System.out.println("\n******** Registered Suppliers ********");
				System.out.println("ID \t Name");

				for (SupplierModel sup : supList) {
					System.out.println(sup.getId() + " \t " + sup.getName());
				}

				System.out.print("\nEnter ID of the supplier you wants to update: ");

				int updateSelect = Integer.parseInt(sc.nextLine().trim());

				SupplierModel selectedSupplier = supplierService.getSupplierDetails(updateSelect);

				System.out.println("\nCurrent details: " + selectedSupplier.getId() + "\t" + selectedSupplier.getName()
						+ "\t" + selectedSupplier.getEmail() + "\t" + selectedSupplier.getAddress() + "\t"
						+ selectedSupplier.getUname());

				System.out.print("New Name: ");
				name = sc.nextLine();

				System.out.print("New Address: ");
				address = sc.nextLine();

				System.out.print("New email: ");
				email = sc.nextLine();

				System.out.print("New Username: ");
				uname = sc.nextLine();

				supplierService.updateSupplier(updateSelect, name, address, email, uname);

			} else if (select == 4) {
				ArrayList<SupplierModel> supList = supplierService.viewSuppliers();

				System.out.println("\n******** Registered Suppliers ********");
				System.out.println("ID \t Name \t\t Username \t\t Email \t\t Address");

				for (SupplierModel sup : supList) {
					System.out.println(sup.getId() + " \t " + sup.getName() + " \t\t " + sup.getUname() + " \t\t "
							+ sup.getEmail() + " \t " + sup.getAddress());
				}

			} else {
				System.out.println("Please select a valid option *****");
			}
		}

	}

}

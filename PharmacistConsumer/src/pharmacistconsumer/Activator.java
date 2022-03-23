package pharmacistconsumer;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pharmacistproducer.PharmacistModel;
import pharmacistproducer.PharmacistService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Pharmacist Consumer started");
		serviceReference = bundleContext.getServiceReference(PharmacistService.class.getName());

		if (serviceReference != null) {
			PharmacistService pharmacistService = (PharmacistService) bundleContext.getService(serviceReference);
			pharmacistMenu(pharmacistService);
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Pharmacist Consumer stopped");
		bundleContext.ungetService(serviceReference);
	}

	private void pharmacistMenu(PharmacistService pharmacistService) {
		int select = 0;
		String name;
		int phoneNumber;
		String email;
		String nic;
		String address;
		String password;

		Scanner sc = new Scanner(System.in);

		while (select != -1) {
			System.out.println("\n================================");
			System.out.println("Pharmacists management:");
			System.out.println("\t1) Add new pharmacist");
			System.out.println("\t2) Remove pharmacist");
			System.out.println("\t3) Update pharmacist");
			System.out.println("\t4) View all pharmacists");

			System.out.print("\nPlease select a option: ");
			select = Integer.parseInt(sc.nextLine().trim());

			if (select == 1) {
				System.out.print("Name: ");
				name = sc.nextLine();

				System.out.print("Phone Number: ");
				phoneNumber = Integer.parseInt(sc.nextLine().trim());

				System.out.print("Email: ");
				email = sc.nextLine();

				System.out.print("NIC: ");
				nic = sc.nextLine();

				System.out.print("Address: ");
				address = sc.nextLine();

				password = "pass123";

				pharmacistService.addPharmacist(name, phoneNumber, email, nic, address, password);

			} else if (select == 2) {
				ArrayList<PharmacistModel> phList = pharmacistService.viewPharmacists();

				System.out.println("\n******** Registered Pharmacists ********");
				System.out.println("ID \t Name");

				for (PharmacistModel ph : phList) {
					System.out.println(ph.getId() + " \t " + ph.getName());
				}

				System.out.print("\nEnter ID of the pharmacist you wants to remove: ");

				int removeSelect = Integer.parseInt(sc.nextLine().trim());

				pharmacistService.removePharmacist(removeSelect);

			} else if (select == 3) {
				ArrayList<PharmacistModel> phList = pharmacistService.viewPharmacists();

				System.out.println("\n******** Registered Pharmacists ********");
				System.out.println("ID \t Name");

				for (PharmacistModel ph : phList) {
					System.out.println(ph.getId() + " \t " + ph.getName());
				}

				System.out.print("\nEnter ID of the pharmacist you wants to update: ");

				int updateSelect = Integer.parseInt(sc.nextLine().trim());

				PharmacistModel selectedPharmacist = pharmacistService.getPharmacistDetails(updateSelect);

				System.out.println(
						"\nCurrent details: " + selectedPharmacist.getId() + "\t" + selectedPharmacist.getName() + "\t"
								+ selectedPharmacist.getPhoneNumber() + "\t" + selectedPharmacist.getEmail() + "\t"
								+ selectedPharmacist.getNic() + "\t" + selectedPharmacist.getAddress());

				System.out.print("New Name: ");
				name = sc.nextLine();

				System.out.print("New Phone no: ");
				phoneNumber = Integer.parseInt(sc.nextLine().trim());

				System.out.print("New email: ");
				email = sc.nextLine();

				System.out.print("New NIC: ");
				nic = sc.nextLine();

				System.out.print("New Address: ");
				address = sc.nextLine();

				pharmacistService.updatePharmacist(updateSelect, name, phoneNumber, email, nic, address);

			} else if (select == 4) {
				ArrayList<PharmacistModel> phList = pharmacistService.viewPharmacists();

				System.out.println("\n******** Registered Pharmacists ********");
				System.out.println("ID \t Name \t\t Phone No \t\t NIC \t\t Email \t\t Address");

				for (PharmacistModel ph : phList) {
					System.out.println(ph.getId() + " \t " + ph.getName() + " \t\t " + ph.getPhoneNumber() + " \t\t "
							+ ph.getNic() + " \t " + ph.getEmail() + " \t " + ph.getAddress());
				}

			} else {
				System.out.println("Please select a valid option *****");
			}
		}

	}
}

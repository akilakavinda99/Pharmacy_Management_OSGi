package medicineconsumer;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import medicineproducer.MedicineModel;
import medicineproducer.MedicineService;

public class Activator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceReference serviceReference;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Medicine Consumer started");
		serviceReference = context.getServiceReference(MedicineService.class.getName());

		if (serviceReference != null) {
			@SuppressWarnings("unchecked")
			MedicineService medicineService = (MedicineService) context.getService(serviceReference);
			medicineMenu(medicineService);
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Medicine Consumer stopped");
		context.ungetService(serviceReference);
	}

	public void medicineMenu(MedicineService medicineService) {
		int select = 0;
		String name;
		float price;
		int qty;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (select != -1) {
			System.out.println("\n================================");
			System.out.println("Medicine management:");
			System.out.println("\t1) Add new medicine");
			System.out.println("\t2) Remove medicine");
			System.out.println("\t3) Update Medicine");
			System.out.println("\t4) View all medicine");

			System.out.print("\nPlease select a option: ");
			select = Integer.parseInt(sc.nextLine().trim());

			if (select == 1) {
				System.out.print("Name : ");
				name = sc.nextLine();

				System.out.print("Price : ");
				price = Float.parseFloat(sc.nextLine().trim());

				System.out.print("QTY : ");
				qty = Integer.parseInt(sc.nextLine().trim());

				medicineService.insertMedicine(name, price, qty);

			} else if (select == 2) {

				ArrayList<MedicineModel> mediList = medicineService.viewMedicines();

				System.out.println("\n******** Available Medicines ********");
				System.out.println("ID \t Name");

				for (MedicineModel medi : mediList) {
					System.out.println(medi.getId() + " \t " + medi.getName());
				}

				System.out.print("\nEnter ID of the medicine you wants to remove: ");

				int removeSelect = Integer.parseInt(sc.nextLine().trim());

				medicineService.removeMedicine(removeSelect);

			} else if (select == 3) {

				ArrayList<MedicineModel> mediList = medicineService.viewMedicines();

				System.out.println("\n******** Available Medicines ********");
				System.out.println("ID \t Name");

				for (MedicineModel medi : mediList) {
					System.out.println(medi.getId() + " \t " + medi.getName());
				}

				System.out.print("\nEnter ID of the medicine you wants to update: ");

				int updateSelect = Integer.parseInt(sc.nextLine().trim());

				MedicineModel selectedMedicine = medicineService.getMedicineDetails(updateSelect);

				System.out.println("\nCurrent details: " + selectedMedicine.getId() + "\t" + selectedMedicine.getName()
						+ "\t" + selectedMedicine.getPrice() + "\t" + selectedMedicine.getQTY());

				System.out.print("New Name : ");
				name = sc.nextLine();

				System.out.print("New Price : ");
				price = Float.parseFloat(sc.nextLine().trim());

				System.out.print("New QTY : ");
				qty = Integer.parseInt(sc.nextLine().trim());

				medicineService.updateMedicine(updateSelect, name, price, qty);

			} else if (select == 4) {
				ArrayList<MedicineModel> mediList = medicineService.viewMedicines();

				System.out.println("\n******** Available Medicines ********");
				System.out.println("ID \t Name \t\t Price \t\t QTY");

				for (MedicineModel medi : mediList) {
					System.out.println(medi.getId() + " \t " + medi.getName() + " \t\t " + medi.getPrice() + " \t\t "
							+ medi.getQTY());
				}

			} else {
				System.out.println("Please select a valid option *****");
			}

		}
	}

}

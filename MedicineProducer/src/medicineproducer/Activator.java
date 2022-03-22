package medicineproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Medicine Producer started");
		MedicineService medicineService = new MedicineServiceImpl();
		serviceRegistration = bundleContext.registerService(MedicineService.class.getName(), medicineService, null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Medicine Producer stopped");
		serviceRegistration.unregister();
	}

}

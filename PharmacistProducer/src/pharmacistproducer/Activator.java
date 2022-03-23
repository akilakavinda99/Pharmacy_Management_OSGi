package pharmacistproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Pharmacist Producer started");
		PharmacistService pharmacistService = new PharmacistServiceImpl();
		serviceRegistration = bundleContext.registerService(PharmacistService.class.getName(), pharmacistService, null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Pharmacist Producer stopped");
		serviceRegistration.unregister();
	}

}

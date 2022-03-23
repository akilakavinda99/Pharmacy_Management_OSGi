package supplierproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Supplier Producer started");
		SupplierService supplierService = new SupplierServiceImpl();
		serviceRegistration = bundleContext.registerService(SupplierService.class.getName(), supplierService, null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Supplier Producer stopped");
		serviceRegistration.unregister();
	}

}

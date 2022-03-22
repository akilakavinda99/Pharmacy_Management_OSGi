package databaseservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Database Service started");

	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Database Service stopped");
	}

}

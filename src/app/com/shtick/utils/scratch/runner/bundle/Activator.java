package com.shtick.utils.scratch.runner.bundle;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.shtick.utils.scratch.runner.Driver;

/**
 **/
public class Activator implements BundleActivator {
	private ServiceRegistration<?> driverRegistration;
	
    /**
     * Implements BundleActivator.start(). Prints
     * a message and adds itself to the bundle context as a service
     * listener.
     * @param context the framework context for the bundle.
     **/
    @Override
	public void start(BundleContext context){
		System.out.println(this.getClass().getCanonicalName()+": Starting.");
		driverRegistration=context.registerService(com.shtick.utils.scratch.runner.Driver.class.getName(), new Driver(),new Hashtable<String, String>());
    }

    /**
     * Implements BundleActivator.stop(). Prints
     * a message and removes itself from the bundle context as a
     * service listener.
     * @param context the framework context for the bundle.
     **/
    @Override
	public void stop(BundleContext context){
		System.out.println(this.getClass().getCanonicalName()+": Stopping.");
		if(driverRegistration!=null)
			driverRegistration.unregister();
    }

}
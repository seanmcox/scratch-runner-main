/**
 * 
 */
package com.shtick.utils.scratch.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.felix.framework.FrameworkFactory;
import org.apache.felix.main.AutoProcessor;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;

/**
 * @author Sean
 *
 */
public class Main{
    private static Framework framework = null;
    private static Object mainService;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // Print welcome banner.
        System.out.println("\nScratch Runner Initializing");
        System.out.println("===========================\n");

        try{
            framework = getFrameworkFactory().newFramework(null);
            framework.init();
            Map<String,String> config=new HashMap<String,String>();
            // Configure auto-deploy of bundles.
            config.put(AutoProcessor.AUTO_DEPLOY_ACTION_PROPERTY, AutoProcessor.AUTO_DEPLOY_INSTALL_VALUE+","+AutoProcessor.AUTO_DEPLOY_UPDATE_VALUE+","+AutoProcessor.AUTO_DEPLOY_START_VALUE);
            
            for(String key:config.keySet())
        		System.getProperties().setProperty(key, config.get(key));
            
			AutoProcessor.process(config, framework.getBundleContext());
			framework.start();
			ServiceReference<?> runtimeReference=framework.getBundleContext().getServiceReference("com.shtick.utils.scratch.runner.core.Main");
			if(runtimeReference==null){
				System.err.println("No ScratchRuntime found.");
				synchronized(config){
					config.wait(30000);
				}
			}
			else{
    			mainService=framework.getBundleContext().getService(runtimeReference);
    			try {
        			Method method = mainService.getClass().getMethod("main", String[].class);
        			method.invoke(mainService, new Object[] {args});
    			}
    			catch(NoSuchMethodException t) {
    				throw new RuntimeException(t);
    			}
			}
        	framework.stop();
        	System.exit(0);
        }
        catch (Exception t){
            System.err.println("Could not create framework: " + t);
            t.printStackTrace();
            System.exit(-1);
        }
    }

    private static FrameworkFactory getFrameworkFactory() throws Exception{
        java.net.URL url = Main.class.getClassLoader().getResource(
            "META-INF/services/org.osgi.framework.launch.FrameworkFactory");
        if (url != null){
            try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){
                for (String s = br.readLine(); s != null; s = br.readLine()){
                    s = s.trim();
                    // Try to load first non-empty, non-commented line.
                    if ((s.length() > 0) && (s.charAt(0) != '#'))
                        return (FrameworkFactory) Class.forName(s).newInstance();
                }
            }
        }

        throw new Exception("Could not find framework factory.");
    }
}

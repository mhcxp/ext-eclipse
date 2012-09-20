/**
 * Copyright 2012 Ext-Eclipse Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.felix.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.apache.felix.framework.util.Util;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

/**
 * Felix的启动入口
 * 
 * @version 1.0 Aug 23, 2012 2:45:00 PM
 */
public class FelixMain {
	/**
	 * Switch for specifying bundle directory.
	 **/
	public static final String BUNDLE_DIR_SWITCH = "-b";

	/**
	 * The property name used to specify whether the launcher should install a
	 * shutdown hook.
	 **/
	public static final String SHUTDOWN_HOOK_PROP = "felix.shutdown.hook";
	/**
	 * The property name used to specify an URL to the system property file.
	 **/
	public static final String SYSTEM_PROPERTIES_PROP = "felix.system.properties";
	/**
	 * The default name used for the system properties file.
	 **/
	public static final String SYSTEM_PROPERTIES_FILE_VALUE = "system.properties";
	/**
	 * The property name used to specify an URL to the configuration property
	 * file to be used for the created the framework instance.
	 **/
	public static final String CONFIG_PROPERTIES_PROP = "felix.config.properties";
	/**
	 * The default name used for the configuration properties file.
	 **/
	public static final String CONFIG_PROPERTIES_FILE_VALUE = "config.properties";
	/**
	 * Name of the configuration directory.
	 */
	public static final String CONFIG_DIRECTORY = "conf";

	protected static Framework m_fwk = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String bundleDir = null;
		String cacheDir = null;
		boolean expectBundleDir = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(BUNDLE_DIR_SWITCH)) {
				expectBundleDir = true;
			} else if (expectBundleDir) {
				bundleDir = args[i];
				expectBundleDir = false;
			} else {
				cacheDir = args[i];
			}
		}

		if ((args.length > 3) || (expectBundleDir && bundleDir == null)) {
			System.out
					.println("Usage: [-b <bundle-deploy-dir>] [<bundle-cache-dir>]");
			System.exit(0);
		}

		FelixMain.loadSystemProperties();

		// Read configuration properties.
		Properties configProps = FelixMain.loadConfigProperties();
		// If no configuration properties were found, then create
		// an empty properties object.
		if (configProps == null) {
			System.err
					.println("No " + CONFIG_PROPERTIES_FILE_VALUE + " found.");
			configProps = new Properties();
		}

		// Copy framework properties from the system properties.
		FelixMain.copySystemProperties(configProps);

		// If there is a passed in bundle auto-deploy directory, then
		// that overwrites anything in the config file.
		if (bundleDir != null) {
			configProps.setProperty(AutoProcessor.AUTO_DEPLOY_DIR_PROPERY,
					bundleDir);
		}

		// If there is a passed in bundle cache directory, then
		// that overwrites anything in the config file.
		if (cacheDir != null) {
			configProps.setProperty(Constants.FRAMEWORK_STORAGE, cacheDir);
		}

		// If enabled, register a shutdown hook to make sure the framework is
		// cleanly shutdown when the VM exits.
		String enableHook = configProps.getProperty(SHUTDOWN_HOOK_PROP);
		if ((enableHook == null) || !enableHook.equalsIgnoreCase("false")) {
			Runtime.getRuntime().addShutdownHook(
					new Thread("Felix Shutdown Hook") {
						public void run() {
							try {
								if (m_fwk != null) {
									m_fwk.stop();
									m_fwk.waitForStop(0);
								}
							} catch (Exception ex) {
								System.err.println("Error stopping framework: "
										+ ex);
							}
						}
					});
		}

		try {
			// Create an instance of the framework.
			FrameworkFactory factory = getFrameworkFactory();
			m_fwk = factory.newFramework((Map) configProps);
			// Initialize the framework, but don't start it yet.
			m_fwk.init();
			// Use the system bundle context to process the auto-deploy
			// and auto-install/auto-start properties.
			AutoProcessor.process(configProps, m_fwk.getBundleContext());
			FrameworkEvent event;
			do {
				// Start the framework.
				m_fwk.start();
				// Wait for framework to stop to exit the VM.
				event = m_fwk.waitForStop(0);
			}
			// If the framework was updated, then restart it.
			while (event.getType() == FrameworkEvent.STOPPED_UPDATE);
			// Otherwise, exit.
			System.exit(0);
		} catch (Exception ex) {
			System.err.println("Could not create framework: " + ex);
			ex.printStackTrace();
			System.exit(0);
		}
	}

	public static void loadSystemProperties() {
		URL propURL = null;
		String custom = System.getProperty(SYSTEM_PROPERTIES_PROP);
		System.out.println("Loading " + custom);
		if (custom != null) {
			try {
				propURL = new URL(custom);
			} catch (MalformedURLException ex) {
				System.err.print("Main: " + ex);
				return;
			}
		} else
			return;

		// Read the properties file.
		Properties props = new Properties();
		InputStream is = null;
		try {
			is = propURL.openConnection().getInputStream();
			props.load(is);
		} catch (FileNotFoundException ex) {
			// Ignore file not found.
		} catch (Exception ex) {
			System.err.println("Main: Error loading system properties from "
					+ propURL);
			System.err.println("Main: " + ex);
			return;
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		// Perform variable substitution on specified properties.
		for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
			String name = String.valueOf(e.nextElement());
			System.setProperty(name,
					Util.substVars(props.getProperty(name), name, null, null));
		}
	}

	public static Properties loadConfigProperties() { // TODO add default value
		URL propURL = null;
		String custom = System.getProperty(CONFIG_PROPERTIES_PROP);
		System.out.println("Loading " + custom);
		if (custom != null) {
			try {
				propURL = new URL(custom);
			} catch (MalformedURLException ex) {
				System.err.println("Main: " + ex);
				return null;
			}
		}
		// Read the properties file.
		Properties props = new Properties();
		InputStream is = null;
		try {
			// Try to load config.properties.
			is = propURL.openConnection().getInputStream();
			props.load(is);
		} catch (Exception ex) {
			return null;
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		// Perform variable substitution for system properties.
		for (Enumeration<?> e = props.propertyNames(); e.hasMoreElements();) {
			String name = String.valueOf(e.nextElement());
			props.setProperty(name,
					Util.substVars(props.getProperty(name), name, null, props));
		}

		return props;
	}

	private static FrameworkFactory getFrameworkFactory() throws Exception {
		URL url = FelixMain.class.getClassLoader().getResource(
				"META-INF/services/org.osgi.framework.launch.FrameworkFactory");
		if (url != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			try {
				for (String s = br.readLine(); s != null; s = br.readLine()) {
					s = s.trim();
					// Try to load first non-empty, non-commented line.
					if ((s.length() > 0) && (s.charAt(0) != '#')) {
						return (FrameworkFactory) Class.forName(s)
								.newInstance();
					}
				}
			} finally {
				if (br != null)
					br.close();
			}
		}

		throw new Exception("Could not find framework factory.");
	}

	public static void copySystemProperties(Properties configProps) {
		for (Enumeration<?> e = System.getProperties().propertyNames(); e
				.hasMoreElements();) {
			String key = String.valueOf(e.nextElement());
			if (key.startsWith("felix.")
					|| key.startsWith("org.osgi.framework.")) {
				configProps.setProperty(key, System.getProperty(key));
			}
		}
	}

}

package com.manpreet.javageek.pattern;

import org.apache.log4j.Logger;

/**
 * 
 * @author msghotra
 * 
 *         this is a simulation of how non thread safe class can allow to create
 *         separate instances
 * 
 */
public class ClassicSingletonNonThreadSafe {

	private static ClassicSingletonNonThreadSafe cs = null;
	private static boolean firstThread = true;
	private static Logger logger = Logger.getRootLogger();

	protected ClassicSingletonNonThreadSafe() {
		// TODO Auto-generated constructor stub
	}

	public static ClassicSingletonNonThreadSafe getInstance() {
		if (cs == null) {
			// simulateRandomActivity();
			synchronized (ClassicSingletonNonThreadSafe.class) {
				if (cs == null) {
					cs = new ClassicSingletonNonThreadSafe();
				}
			}
		}
		return cs;
	}

	private static void simulateRandomActivity() {
		try {
			if (firstThread) {
				firstThread = false;
				logger.info("first thread goes to sleep ...");
				Thread.currentThread().sleep(70);
			}
		} catch (InterruptedException e) {
		}
	}

}

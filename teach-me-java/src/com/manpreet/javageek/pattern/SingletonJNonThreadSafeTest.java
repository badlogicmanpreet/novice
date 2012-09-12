package com.manpreet.javageek.pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * 
 * @author msghotra
 * 
 *         junit test case for testing 'how 2 threads can break singleton
 *         pattern when not thread safe'
 * 
 */
public class SingletonJNonThreadSafeTest extends TestCase {

	private static Logger logger = Logger.getRootLogger();
	private static ClassicSingletonNonThreadSafe cs = null;

	public SingletonJNonThreadSafeTest(String name) {
		super(name);
	}

	public void setUp() {
		PropertyConfigurator.configure("log4j.properties");
		cs = null;
	}

	public void testUnique() throws InterruptedException {
		Thread thread1 = new Thread(new SingletonJNonThreadSafeTestRunnable());
		Thread thread2 = new Thread(new SingletonJNonThreadSafeTestRunnable());
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}

	private static class SingletonJNonThreadSafeTestRunnable implements Runnable {

		@Override
		public void run() {
			ClassicSingletonNonThreadSafe csnts = ClassicSingletonNonThreadSafe.getInstance();

			synchronized (SingletonJNonThreadSafeTest.class) {
				if (cs == null) {
					cs = csnts;
				}
			}

			Assert.assertEquals(true, cs == csnts);
		}

	}
}

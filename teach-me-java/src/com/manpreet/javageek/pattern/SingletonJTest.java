package com.manpreet.javageek.pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * 
 * @author msghotra
 * 
 *         junit test case for testing classic singleton pattern
 * 
 */

public class SingletonJTest extends TestCase {

	private ClassicSingleton csOne = null, csTwo = null;
	private static Logger logger = Logger.getRootLogger();

	public SingletonJTest(String name) {
		super(name);
	}

	// TODO - load same class with different class loaders
	public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("getting singleton ...");
		ClassLoader loader1 = Thread.currentThread().getContextClassLoader();
		ClassicSingleton class1 = (ClassicSingleton) loader1.loadClass("com.manpreet.javageek.pattern.ClassicSingleton").newInstance();
		csOne = class1.getInstance();
		logger.info("got singleton ..." + csOne);
		logger.info("getting singleton ...");
		ClassLoader loader2 = ClassicSingleton.class.getClassLoader();
		ClassicSingleton class2 = (ClassicSingleton) loader2.loadClass("com.manpreet.javageek.pattern.ClassicSingleton").newInstance();
		csTwo = class2.getInstance();
		logger.info("got singleton ..." + csTwo);
	}

	public void testUnique() {
		logger.info("checking singletons for equality");
		Assert.assertEquals(true, csOne == csTwo);
	}
}

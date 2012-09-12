package com.manpreet.javageek.pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author msghotra
 * 
 *         junit test case for testing how serialization breaks singleton
 *         behavior ... always use readResolve()
 * 
 */

public class SingletonJSerializableTest extends TestCase {

	private Logger logger = Logger.getRootLogger();
	private ClassicSingletonSerialized cs1 = null, cs2 = null;

	public SingletonJSerializableTest(String name) {
		super(name);
	}

	public void setUp() {
		PropertyConfigurator.configure("log4j.properties");
		cs1 = ClassicSingletonSerialized.cs;
		cs2 = ClassicSingletonSerialized.cs;
	}

	public void testSerialization() {
		logger.info("in serializable test ...");
		writeCS();
		ClassicSingletonSerialized csA = readCS();
		ClassicSingletonSerialized csB = readCS();
		Assert.assertEquals(true, csA == csB);
	}

	private void writeCS() {
		try {
			FileOutputStream os = new FileOutputStream("serialized.txt");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ClassicSingletonSerialized csS = ClassicSingletonSerialized.cs;
			oos.writeObject(csS);
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ClassicSingletonSerialized readCS() {
		ClassicSingletonSerialized csS = null;
		try {
			FileInputStream is = new FileInputStream("serialized.txt");
			ObjectInputStream ois = new ObjectInputStream(is);
			csS = (ClassicSingletonSerialized) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return csS;
	}

	public void testEquality() {
		logger.info("in test Equality ...");
		Assert.assertEquals(true, cs1 == cs2);
	}
}

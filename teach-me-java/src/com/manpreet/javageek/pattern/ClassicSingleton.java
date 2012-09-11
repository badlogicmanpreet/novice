package com.manpreet.javageek.pattern;

/**
 * 
 * @author msghotra
 * 
 *         this is a classic singleton class
 * 
 *         note: 1. <b>protected</b> constructor breaks the pattern as it allows
 *         the classes within the package & subsclasses to create new instances
 *         2. <b>private</b> constructor will avoid the above but will not let
 *         subclasses get created 3. classes loaded by separate
 *         <b>classloaders</b> will be able to create separate instances 4. this
 *         is not <b>threadsafe</b>, 2 separate threads will be able to create 2
 *         instances after if (cs == null) 5. if i <b>serialize</b> an instance
 *         & then deserialize it 2twice i will have 2 instances
 * 
 * 
 * 
 */
public class ClassicSingleton {

	private static ClassicSingleton cs = null;

	protected ClassicSingleton() {

	}

	public static ClassicSingleton getInstance() {
		if (cs == null) {
			cs = new ClassicSingleton();
			return cs;
		} else {
			return cs;
		}
	}

	public void testSingleton() {
		System.out.println("test singleton invoked");
	}

}

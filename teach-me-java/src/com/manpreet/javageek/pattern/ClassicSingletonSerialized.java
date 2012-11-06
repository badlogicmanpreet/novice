package com.manpreet.javageek.pattern;

import java.io.Serializable;

/**
 * 
 * @author msghotra
 * 
 *         serialized singleton test
 * 
 */

public class ClassicSingletonSerialized implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ClassicSingletonSerialized cs = new ClassicSingletonSerialized();

	protected ClassicSingletonSerialized() {

	}

	public Object readResolve() {
		return cs;
	}

}

package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantDouble extends Constant {

	private double bytes;

	public ConstantDouble(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readDouble());
	}

	public ConstantDouble(double bytes) {
		super(Constants.CONSTANT_Double);
		this.bytes = bytes;
	}

	public double getBytes() {
		return bytes;
	}
}

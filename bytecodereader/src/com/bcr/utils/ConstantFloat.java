package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantFloat extends Constant {

	private float bytes;

	public ConstantFloat(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readFloat());
	}

	public ConstantFloat(float bytes) {
		super(Constants.CONSTANT_Float);
		this.bytes = bytes;
	}

	public float getBytes() {
		return bytes;
	}
}

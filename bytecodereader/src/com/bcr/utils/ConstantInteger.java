package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantInteger extends Constant {

	private int bytes;

	public ConstantInteger(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readInt());
	}

	public ConstantInteger(int bytes) {
		super(Constants.CONSTANT_Integer);
		this.bytes = bytes;
	}

	public int getBytes() {
		return bytes;
	}

}

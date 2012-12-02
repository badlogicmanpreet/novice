package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantLong extends Constant {

	private long bytes;

	public ConstantLong(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readLong());
	}

	public ConstantLong(long bytes) {
		super(Constants.CONSTANT_Long);
		this.bytes = bytes;
	}

	public long getBytes(){
		return bytes;
	}
}

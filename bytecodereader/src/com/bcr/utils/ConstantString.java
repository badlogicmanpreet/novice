package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantString extends Constant {

	private int string_index;

	public ConstantString(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readUnsignedShort());
	}

	public ConstantString(int string_index) {
		super(Constants.CONSTANT_String);
		this.string_index = string_index;
	}

	public int getStringIndex() {
		return string_index;
	}
}

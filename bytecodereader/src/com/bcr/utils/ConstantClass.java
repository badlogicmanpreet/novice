package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantClass extends Constant {

	private int name_index;

	public ConstantClass(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readUnsignedShort());
	}

	public ConstantClass(int name_index) {
		super(Constants.CONSTANT_Class);
		this.name_index = name_index;
	}

	public int getNameIndex() {
		return name_index;
	}
}

package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantNameAndType extends Constant {

	private int name_index;
	private int signature_index;

	public ConstantNameAndType(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readUnsignedShort(), clazzFile.readUnsignedShort());
	}

	public ConstantNameAndType(int name_index, int signature_index) {
		super(Constants.CONSTANT_NameAndType);
		this.name_index = name_index;
		this.signature_index = signature_index;
	}

	public int getNameIndex() {
		return name_index;
	}

	public int getSignatureIndex() {
		return signature_index;
	}
}

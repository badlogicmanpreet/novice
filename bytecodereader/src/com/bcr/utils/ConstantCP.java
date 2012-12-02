package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantCP extends Constant {

	protected int class_index, name_and_type_index;

	public ConstantCP(byte tag, DataInputStream clazzFile) throws IOException {
		this(tag, clazzFile.readUnsignedShort(), clazzFile.readUnsignedShort());
	}

	public ConstantCP(byte tag, int class_index, int name_and_type_index) {
		super(tag);
		this.class_index = class_index;
		this.name_and_type_index = name_and_type_index;
	}

	public int getClassIndex() {
		return class_index;
	}

	public int getNameAndTypeIndex() {
		return name_and_type_index;
	}
}

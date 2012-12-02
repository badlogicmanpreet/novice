package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantInterfaceMethodref extends ConstantCP {

	public ConstantInterfaceMethodref(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		super(Constants.CONSTANT_InterfaceMethodref, clazzFile);
	}

}

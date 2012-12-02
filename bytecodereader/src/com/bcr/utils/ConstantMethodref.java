package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantMethodref extends ConstantCP {

	public ConstantMethodref(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		super(Constants.CONSTANT_Methodref, clazzFile);
	}

}

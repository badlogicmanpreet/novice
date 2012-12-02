package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantFieldref extends ConstantCP {

	public ConstantFieldref(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		super(Constants.CONSTANT_Fieldref, clazzFile);
	}

}

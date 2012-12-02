package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantUtf8 extends Constant {

	private String bytes;

	public ConstantUtf8(DataInputStream clazzFile) throws IOException {
		// TODO Auto-generated constructor stub
		super(Constants.CONSTANT_Utf8);
		bytes = clazzFile.readUTF();
	}

	public String getBytes() {
		return bytes;
	}
}

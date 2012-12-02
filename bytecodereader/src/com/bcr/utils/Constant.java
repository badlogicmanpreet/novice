package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class Constant {

	protected byte tag;

	public Constant(byte tag) {
		// TODO Auto-generated constructor stub
		this.tag = tag;
	}

	static final Constant readConstant(DataInputStream clazzFile) {
		try {
			// read the tag byte
			byte b = clazzFile.readByte();
			switch (b) {
			case Constants.CONSTANT_Class:
				return new ConstantClass(clazzFile);
			case Constants.CONSTANT_Fieldref:
				return new ConstantFieldref(clazzFile);
			case Constants.CONSTANT_Methodref:
				return new ConstantMethodref(clazzFile);
			case Constants.CONSTANT_InterfaceMethodref:
				return new ConstantInterfaceMethodref(clazzFile);
			case Constants.CONSTANT_String:
				return new ConstantString(clazzFile);
			case Constants.CONSTANT_Integer:
				return new ConstantInteger(clazzFile);
			case Constants.CONSTANT_Float:
				return new ConstantFloat(clazzFile);
			case Constants.CONSTANT_Long:
				return new ConstantLong(clazzFile);
			case Constants.CONSTANT_Double:
				return new ConstantDouble(clazzFile);
			case Constants.CONSTANT_NameAndType:
				return new ConstantNameAndType(clazzFile);
			case Constants.CONSTANT_Utf8:
				return new ConstantUtf8(clazzFile);
			default:
				System.out.println("Invalid byte tag in constant pool");
				// throw new ClassFormatException("Invalid byte tag in constant
				// pool: " + b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public byte getTag() {
		return tag;
	}
}

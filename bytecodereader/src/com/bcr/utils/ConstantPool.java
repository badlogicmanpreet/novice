package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantPool {

	int constantPoolCount;
	Constant[] constant;

	public ConstantPool(DataInputStream clazzFile) {
		// TODO Auto-generated constructor stub
		try {
			constantPoolCount = clazzFile.readUnsignedShort();
			constant = new Constant[constantPoolCount];

			// constant[0] is unused
			for (int i = 1; i < constantPoolCount; i++) {
				constant[i] = Constant.readConstant(clazzFile);
			}

			for (int i = 1; i < constant.length; i++) {
				// i want to iterate the ConstantPool
				System.out.println("Constant is : " + constant[i]);
				Constant c = getConstant(i);
				String bytes = constantToString(c);
				System.out.println("Constant as String is : " + bytes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Constant getConstant(int index) {
		return constant[index];
	}

	public String constantToString(Constant c) {
		String string;
		byte tag = c.getTag();
		int i;

		switch (tag) {
		case Constants.CONSTANT_Class:
			i = ((ConstantClass) c).getNameIndex();
			c = getConstant(i);
			string = ((ConstantUtf8) c).getBytes();
			return string;
		case Constants.CONSTANT_Utf8:
			string = ((ConstantUtf8) c).getBytes();
			return string;
		case Constants.CONSTANT_String:
			i = ((ConstantString) c).getStringIndex();
			c = getConstant(i);
			string = ((ConstantUtf8) c).getBytes();
			return string;
		case Constants.CONSTANT_Double:
			string = "" + ((ConstantDouble) c).getBytes();
			return string;
		case Constants.CONSTANT_Integer:
			string = "" + ((ConstantInteger) c).getBytes();
			return string;
		case Constants.CONSTANT_Float:
			string = "" + ((ConstantFloat) c).getBytes();
			return string;
		case Constants.CONSTANT_Long:
			string = "" + ((ConstantLong) c).getBytes();
			return string;
		case Constants.CONSTANT_NameAndType:
			int j = ((ConstantNameAndType) c).getNameIndex();
			Constant cName = getConstant(j);
			String stringName = constantToString(cName);
			int k = ((ConstantNameAndType) c).getSignatureIndex();
			Constant cType = getConstant(k);
			String stringType = constantToString(cType);
			string = stringName + " " + stringType;
			return string;
		case Constants.CONSTANT_Fieldref:
		case Constants.CONSTANT_Methodref:
		case Constants.CONSTANT_InterfaceMethodref:
			int l = ((ConstantCP) c).getClassIndex();
			Constant cClass = getConstant(l);
			String stringClass = constantToString(cClass);
			int m = ((ConstantCP) c).getNameAndTypeIndex();
			Constant cNameAndType = getConstant(m);
			String stringNameAndType = constantToString(cNameAndType);
			string = stringClass + " " + stringNameAndType;
			return string;
		default:
			System.out.println("<TODO>");
		}
		return null;
	}
}

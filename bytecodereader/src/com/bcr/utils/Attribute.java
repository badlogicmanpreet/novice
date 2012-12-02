package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

/*
 attribute_info {
 u2 attribute_name_index;
 u4 attribute_length;
 u1 info[attribute_length];
 }
 */

public abstract class Attribute {

	public static Attribute readAttribute(DataInputStream clazzFile, ConstantPool constantPool) throws IOException {
		int nameIndex;
		String attributeName;
		ConstantUtf8 constantUtf8;
		int attributeLength;

		byte tag = Constants.ATTR_UNKNOWN;

		// u2 attribute_name_index
		nameIndex = clazzFile.readUnsignedShort();
		constantUtf8 = (ConstantUtf8) constantPool.getConstant(nameIndex);
		attributeName = constantUtf8.getBytes();
		System.out.println("attributeName is : " + attributeName);

		// u4 attribute_length
		attributeLength = clazzFile.readInt();
		System.out.println("attributeLength is :" + attributeLength);

		// u1 info[attribute_length]
		for (byte i = 0; i < Constants.KNOWN_ATTRIBUTES; i++) {
			if (attributeName.equals(Constants.ATTRIBUTE_NAMES[i])) {
				tag = i;
				break;
			}
		}

		System.out.println("This is a " + attributeName + " tag");

		return null;
	}

}

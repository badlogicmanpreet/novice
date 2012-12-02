package com.bcr.utils;

import java.io.DataInputStream;
import java.io.IOException;

/*
 field_info {
 u2 access_flags;
 u2 name_index;
 u2 descriptor_index;
 u2 attributes_count;
 attribute_info attributes[attributes_count];
 }

 method_info {
 u2 access_flags;
 u2 name_index;
 u2 descriptor_index;
 u2 attributes_count;
 attribute_info attributes[attributes_count];
 }
 */

public abstract class FieldOrMethod {

	public int accessFlags;
	public int nameIndex;
	public int signatureIndex;
	public ConstantPool constantPool;
	public int attributesCount;
	public Attribute[] attributes;

	public FieldOrMethod(DataInputStream clazzFile, ConstantPool constantPool) throws IOException {
		// TODO Auto-generated constructor stub
		this(clazzFile.readUnsignedShort(), clazzFile.readUnsignedShort(), clazzFile.readUnsignedShort(), null, constantPool);
		attributesCount = clazzFile.readUnsignedShort();
		attributes = new Attribute[attributesCount];
		for (int i = 0; i < attributesCount; i++) {
			attributes[i] = Attribute.readAttribute(clazzFile, constantPool);
		}
	}

	public FieldOrMethod(int accessFlags, int nameIndex, int signatureIndex, Attribute[] attributes, ConstantPool constantPool) {
		this.accessFlags = accessFlags;
		this.nameIndex = nameIndex;
		this.signatureIndex = signatureIndex;
		this.constantPool = constantPool;

	}

}

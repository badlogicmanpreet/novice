package com.bcr.utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BCRMain {

/*	ClassFile {
    	u4 magic;
    	u2 minor_version;
    	u2 major_version;
    	u2 constant_pool_count;
    	cp_info constant_pool[constant_pool_count-1];
    	u2 access_flags;
    	u2 this_class;
    	u2 super_class;
    	u2 interfaces_count;
    	u2 interfaces[interfaces_count];
    	u2 fields_count;
    	field_info fields[fields_count];
    	u2 methods_count;
    	method_info methods[methods_count];
    	u2 attributes_count;
    	attribute_info attributes[attributes_count];
    } */

	DataInputStream clazzFile;
	
	//clazz file fields
	public int magicID;
	public int minor;
	public int major;
	
	public ConstantPool constantPool;
	
	public int access_flags;
	public int this_class;
	public int super_class;
	
	public int[] interfaces;
	public Field[] fields;
	public Method[] methods;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		BCRMain  bcrMain = new BCRMain();
		bcrMain.readClassFile(args[0]);
		bcrMain.byteCodeReader();
	}

	private void readClassFile(String classFile){
	   try {
		   clazzFile = new DataInputStream(new BufferedInputStream(new FileInputStream(classFile)));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	private void byteCodeReader(){
		try {
			
			/********************First get the Headers*******************/
			//read magic tag
			readMagicID();
			
			//read minor & major version
			readVersion();
			
			/********************Prepare Constant Pool*******************/
	        //read constant pool entries
			readConstantPool();
			
			/********************Read Class Info*******************/
			//read class info
			readClassInfo();
			
			/********************Read Interfaces*******************/
			//read interfaces
			readInterfaces();

			/********************Read Fields*******************/
			//read fields
			readFields();

			/********************Read Methods*******************/
			//read Methods
			readMethods();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void readMagicID() throws IOException{
		int magic = 0xCAFEBABE;
		magicID = clazzFile.readInt();
		if(magicID != magic){
			System.out.println("This is not a valid java class");
		}
		else{
          System.out.println("Magic ID is : " + magicID);			
		}
	}
	
	private void readVersion() throws IOException{
	    minor = clazzFile.readUnsignedShort();
		major = clazzFile.readUnsignedShort();
		System.out.println("Minor version is : " + minor);
		System.out.println("Major version is : " + major);
	}
	
	private void readConstantPool(){
		constantPool = new ConstantPool(clazzFile);
	}
	
	private void readClassInfo() throws IOException{
		   access_flags = clazzFile.readUnsignedShort();
    	   this_class = clazzFile.readUnsignedShort();
    	   super_class = clazzFile.readUnsignedShort();
    	   System.out.println("Class Info is : " + access_flags + " " + this_class + " " + super_class);
	}
	
	private void readInterfaces() throws IOException{
		int interfaceCount;
		
		interfaceCount = clazzFile.readUnsignedShort();
		System.out.println("Interface count is :" + interfaceCount);
		
		interfaces = new int[interfaceCount];
		for(int i = 0; i < interfaceCount; i++){
			interfaces[i] = clazzFile.readUnsignedShort();
			System.out.println("Interface " + i + " is : " + interfaces[i]);
		}
	}
	
	private void readFields() throws IOException{
	int fieldsCount;
	
	fieldsCount = clazzFile.readUnsignedShort();
	fields = new Field[fieldsCount];
	for(int i=0; i<fieldsCount; i++){
	  fields[i] = new Field(clazzFile, constantPool);	
	}
	}
	
	private void readMethods() throws IOException{
		int methodsCount;
		
		methodsCount = clazzFile.readUnsignedShort();
		methods = new Method[methodsCount];
		for(int i=0; i<methodsCount; i++){
		  methods[i] = new Method(clazzFile, constantPool);	
		}
		}

}

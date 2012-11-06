package com.manpreet.javageek.namingservice;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileSystemJNDI {

	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");

		Hashtable env = new Hashtable();
		env.put(Context.PROVIDER_URL, "file://C:/Test");
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

		try {
			InitialContext ic = new InitialContext(env);

			Object object = (Object) ic.lookup("cancelpblpoDtl.txt");
			logger.info(object);

			NamingEnumeration ne = ic.listBindings("");
			while (ne.hasMore()) {
				Binding binding = (Binding) ne.next();
				logger.info(binding.getName());
				logger.info(binding.getObject());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}

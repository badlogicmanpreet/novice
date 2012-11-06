package com.voyager.processor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;

import com.voyager.ifaces.IProcessor;

public class StageProcessor implements IProcessor{

	public String process(String[] columns, HashMap result) throws SQLException, MessagingException {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<P ALIGN='left'><TABLE BORDER=8  bgcolor=\"Silver\"><caption ALIGN='left'></caption>");
		// table header
		buffer.append("<TR bgcolor=\"lime\">");
		for (int i = 0; i < columns.length; i++) {
			buffer.append("<TH>" + columns[i] + "</TH>");
		}
		buffer.append("</TR>");
		Set keys = result.keySet();
		Iterator keysIter = keys.iterator();
		while (keysIter.hasNext()) {
			// System.out.println("key: " + keysIter.next());
			String key = (String) keysIter.next();
			buffer.append("<TR>");
			buffer.append("<TD>" + key + "</TD>");
			buffer.append("<TD>" + result.get(key) + "</TD>");
			buffer.append("</TR>");
		}
		buffer.append("</TABLE></P>");

		return buffer.toString();
	}

	public String process(ResultSet resultSet, Properties properties) throws SQLException, MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap process(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
}

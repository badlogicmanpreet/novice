package com.voyager.processor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

import com.voyager.ifaces.IProcessor;

public class HospProcessor implements IProcessor {

	public String process(ResultSet resultSet, Properties properties) throws SQLException, MessagingException {

		int rowCount = 0;

		StringBuffer buffer = new StringBuffer();
		buffer
				.append("<P ALIGN='left'><TABLE BORDER=8  bgcolor=\"Silver\"><caption ALIGN='left'><I>Following is the count of broken records in the hospital...</I></caption>");
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnCount = rsmd.getColumnCount();
		// table header
		buffer.append("<TR bgcolor=\"lime\">");
		for (int i = 0; i < columnCount; i++) {
			buffer.append("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
		}
		buffer.append("</TR>");
		while (resultSet.next()) {
			// the data
			rowCount++;
			buffer.append("<TR>");
			for (int i = 0; i < columnCount; i++) {
				buffer.append("<TD>" + resultSet.getString(i + 1) + "</TD>");
			}
			buffer.append("</TR>");
		}
		buffer.append("</TABLE></P>");

		return buffer.toString() + "|" + rowCount;
	}

	public String process(String[] columns, HashMap result) throws SQLException, MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap process(ResultSet resultSet) {
		HashMap hospitalResult = new HashMap();

		System.out.println("Result set is = " + resultSet.toString());
		ResultSetMetaData rsmd;
		try {
			rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			while (resultSet.next()) {
				hospitalResult.put(resultSet.getString(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hospitalResult;
	}
}

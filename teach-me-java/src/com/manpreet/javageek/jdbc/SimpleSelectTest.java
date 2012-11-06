package com.manpreet.javageek.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * 
 * @author msghotra
 * 
 *         run a select query
 * 
 */
public class SimpleSelectTest extends TestCase {

	Logger logger = Logger.getRootLogger();
	String url = "jdbc:oracle:thin:@//localhost:1521/XE";

	public void testSelect() {
		try {
			PropertyConfigurator.configure("log4j.properties");
			Connection conn = DriverManager.getConnection(url, "sys as sysdba", "Oracle11g");
			conn.setAutoCommit(false);

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from dual");

			String result = null;
			while (rs.next()) {
				result = rs.getString(1);
			}

			logger.info("Result is: " + result);
			Assert.assertEquals("X", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

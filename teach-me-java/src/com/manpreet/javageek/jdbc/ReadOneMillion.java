package com.manpreet.javageek.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ReadOneMillion {

	Logger logger = Logger.getRootLogger();

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		try {
			new ReadOneMillion().read();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void read() throws SQLException, IOException {
		long startTime = System.currentTimeMillis();
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "sys as sysdba", "Oracle11g");

		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery("select * from dev_test1");
		rs.setFetchSize(100);

		// FileOutputStream fos = new FileOutputStream(new
		// File("dev_test1.txt"));
		FileWriter fw = new FileWriter(new File("dev_test1.txt"));
		BufferedWriter bw = new BufferedWriter(fw);
		while (rs.next()) {
			bw.write(rs.getString(1));
			bw.write(" ");
			bw.write(rs.getString(2));
			bw.write(" ");
			bw.write(rs.getString(3));
			bw.newLine();
		}
		fw.flush();
		bw.flush();
		conn.close();
		logger.info(System.currentTimeMillis() - startTime);
	}

}

package com.voyager.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.voyager.ifaces.IConnection;

public class RMSConnection implements IConnection {

	private java.sql.Connection connection;

	private String dbServer;

	private String dbName;

	private String dbUser;

	private String dbPassword;

	private String dbPort;

	public void setConnection(Properties properties) throws SQLException {

		this.dbServer = properties.getProperty("rmsdbServer");
		this.dbName = properties.getProperty("rmsdbName");
		this.dbUser = properties.getProperty("rmsdbUser");
		this.dbPassword = properties.getProperty("rmsdbPassword");
		this.dbPort = properties.getProperty("rmsdbPort");

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		connection = DriverManager.getConnection("jdbc:oracle:thin:@" + this.dbServer + ":" + this.dbPort + ":" + this.dbName, this.dbUser, this.dbPassword);
	}

	public java.sql.Connection getConnection() {
		return connection;
	}
}

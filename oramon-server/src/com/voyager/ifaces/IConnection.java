package com.voyager.ifaces;

import java.sql.SQLException;
import java.util.Properties;

public interface IConnection {

	public void setConnection(Properties properties) throws SQLException;

	public java.sql.Connection getConnection();

}

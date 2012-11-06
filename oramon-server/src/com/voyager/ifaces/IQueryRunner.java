package com.voyager.ifaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

public interface IQueryRunner {

	public String queryForHosp(Connection connection, Properties properties) throws SQLException, MessagingException;

	public HashMap queryForHospital(Connection connection, Properties properties) throws SQLException, MessagingException;

	public String queryForStage(Connection connection, Properties properties) throws SQLException, MessagingException;

	public HashMap queryForEgate(Properties properties) throws FileNotFoundException, IOException, SQLException, MessagingException;
}

package com.voyager.ifaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

public interface IProcessor {

	public String process(ResultSet resultSet, Properties properties) throws SQLException, MessagingException;

	public String process(String[] columns, HashMap result) throws SQLException, MessagingException;

	public HashMap process(ResultSet resultSet);
}

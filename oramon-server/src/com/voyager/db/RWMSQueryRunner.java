package com.voyager.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.MessagingException;

import com.voyager.processor.HospProcessor;

public class RWMSQueryRunner {

	public static final String SEP = System.getProperty("file.separator");

	private Statement statementForQuery;

	private Statement statementForInsertQuery;

	private Statement statementForRATFile;

	private String selectQuery;

	private String logQuery;

	private String insertQuery;

	private ResultSet resultSet;

	private ResultSet resultSetRATFile;

	private HospProcessor hospProcessor;

	//private HospitalResultProcessorForRMSRATFile hospitalResultProcessorForRATFile;

	private String hospitalResult;

	public String run(Connection connection, Properties properties, FileWriter rwmsFileWriter) throws SQLException, MessagingException,
			IOException {

		selectQuery = properties.getProperty("selectQuery");
		logQuery = properties.getProperty("logQuery");
		insertQuery = properties.getProperty("insertQuery");
		statementForQuery = connection.createStatement();
		resultSet = statementForQuery.executeQuery(selectQuery);
		
		hospProcessor = new HospProcessor();
		hospitalResult = hospProcessor.process(resultSet, properties);

//		hospitalResultProcessorForRATFile = new HospitalResultProcessorForRMSRATFile();
//		resultSetRATFile = getResultSetForRATFile(connection);
//		hospitalResultProcessorForRATFile.process(resultSetRATFile, properties, rwmsFileWriter);

		statementForInsertQuery = connection.createStatement();
		statementForInsertQuery.executeUpdate(insertQuery);
		
		return hospitalResult;
	}

	private ResultSet getResultSetForRATFile(Connection connection) throws SQLException {
		statementForRATFile = connection.createStatement();
		resultSetRATFile = statementForRATFile.executeQuery(logQuery);
		return resultSetRATFile;
	}

}


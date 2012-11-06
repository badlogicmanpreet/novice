package com.voyager.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

import com.voyager.processor.HospProcessor;
import com.voyager.processor.StageProcessor;
import com.voyager.ifaces.IQueryRunner;

public class RMSQueryRunner implements IQueryRunner {

	public static final String SEP = System.getProperty("file.separator");

	private Statement statementForQuery;

	private String selectQuery;

	private String stageCountQuery;

	private String stageTables;

	private String stageResult;

	private ResultSet resultSet;

	private HashMap result;

	private HospProcessor hospProcessor;

	private StageProcessor stageProcessor;

	private HashMap hospitalResult;

	public String queryForHosp(Connection connection, Properties properties) throws SQLException, MessagingException {

		selectQuery = properties.getProperty("selectQuery");
		statementForQuery = connection.createStatement();
		resultSet = statementForQuery.executeQuery(selectQuery);

		//hospProcessor = new HospProcessor();
		//hospitalResult = hospProcessor.process(resultSet, properties);

		//return hospitalResult;
		return null;
	}

	public HashMap queryForHospital(Connection connection, Properties properties) throws SQLException, MessagingException {

		hospProcessor = new HospProcessor();
		selectQuery = properties.getProperty("selectQuery");
		System.out.println("selectQuery is = " + selectQuery);
		statementForQuery = connection.createStatement();
		resultSet = statementForQuery.executeQuery(selectQuery);

		//hospProcessor = new HospProcessor();
		//hospitalResult = hospProcessor.process(resultSet, properties);

		System.out.println("Heading for processing");
		hospitalResult = hospProcessor.process(resultSet);
		
		return hospitalResult;
	}

	public String queryForStage(Connection connection, Properties properties) throws SQLException, MessagingException {
		stageCountQuery = properties.getProperty("mfqueueCountQuery");
		stageTables = properties.getProperty("stageTables");
		String[] stageTablesList = stageTables.split("\\|");
		result = new HashMap();
		for (int i = 0; i < stageTablesList.length; i++) {
			statementForQuery = connection.createStatement();
			resultSet = statementForQuery.executeQuery(stageCountQuery + " " + stageTablesList[i]);
			while (resultSet.next()) {
				result.put(stageTablesList[i], resultSet.getString(1));
			}
		}
		String[] columns = { "Staging Table", "No. of Records Pending" };
		stageProcessor = new StageProcessor();
		stageResult = stageProcessor.process(columns, result);
		return stageResult;
	}

	public HashMap queryForEgate(Properties properties) throws FileNotFoundException, IOException, SQLException, MessagingException {
		// TODO Auto-generated method stub
		return null;
	}
}

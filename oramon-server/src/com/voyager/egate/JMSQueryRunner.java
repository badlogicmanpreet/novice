package com.voyager.egate;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

import com.voyager.ifaces.IQueryRunner;
import com.voyager.processor.EgateProcessor;

public class JMSQueryRunner implements IQueryRunner {

	public static final String SEP = System.getProperty("file.separator");

	private String topic;

	private String messageCount;

	private String jmsResult;

	private EgateProcessor egateProcessor;

	public HashMap queryForEgate(Properties properties) throws FileNotFoundException, IOException, SQLException, MessagingException {
		String strLine;
		HashMap processMap = new HashMap();

		FileInputStream fstream = new FileInputStream(properties.getProperty("installDir") + SEP + "Topic.txt");
		DataInputStream inputStream = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		Runtime r = Runtime.getRuntime();
		Process p = null;

		while ((strLine = br.readLine()) != null) {
			this.topic = strLine;
			try {
				System.out.println("topic name is = " + topic);
				p = r.exec(properties.getProperty("scriptDir") + SEP + "RIBTopicScan.sh " + this.topic);
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					if (line.startsWith("Message")) {
						messageCount = line.substring(line.indexOf(":") + 2, line.length());
					}
				}
				processMap.put(this.topic, messageCount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] columns = { "Topic", "No. of Messages" };
		egateProcessor = new EgateProcessor();
		jmsResult = egateProcessor.process(columns, processMap);
		System.out.println("jmsResult = " + jmsResult);

		//return jmsResult;
		return processMap;
	}

	public String queryForHosp(Connection connection, Properties properties) throws SQLException, MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

	public String queryForStage(Connection connection, Properties properties) throws SQLException, MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap queryForHospital(Connection connection, Properties properties) throws SQLException, MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

}

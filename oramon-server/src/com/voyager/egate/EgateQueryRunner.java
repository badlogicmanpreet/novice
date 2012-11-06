package com.voyager.egate;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

import com.voyager.ifaces.IQueryRunner;
import com.voyager.processor.EgateProcessor;

public class EgateQueryRunner implements IQueryRunner {

	public static final String SEP = System.getProperty("file.separator");

	private String status;

	private String processName;

	private String processResult;

	private EgateProcessor egateProcessor;

	public HashMap queryForEgate(Properties properties) throws IOException, SQLException, MessagingException {
		String strLine;
		HashMap processMap = new HashMap();

		FileInputStream fstream = new FileInputStream(properties.getProperty("installDir") + SEP + "EgateProcesses.txt");
		DataInputStream inputStream = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		Runtime r = Runtime.getRuntime();
		Process p = null;

		while ((strLine = br.readLine()) != null) {
			this.processName = strLine;
			try {
				p = r.exec(properties.getProperty("installDir") + SEP + "RIBProcessesStatus.sh " + this.processName);

				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					if (line.startsWith("State")) {
						status = line.substring(line.indexOf(":") + 2, line.length());
					}
				}
				if (this.processName.equalsIgnoreCase("stcregd")) {
					status = "Up";
				}
				processMap.put(this.processName, status);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] columns = { "Process", "Status" };
		egateProcessor = new EgateProcessor();
		processResult = egateProcessor.process(columns, processMap);
		//return processResult;
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

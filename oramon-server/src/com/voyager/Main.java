package com.voyager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.MessagingException;

import com.voyager.cloud.AuthTokenService;
import com.voyager.cloud.MessageService;
import com.voyager.db.RMSConnection;
import com.voyager.db.RMSQueryRunner;
import com.voyager.db.RWMSConnection;
import com.voyager.db.RWMSQueryRunner;
import com.voyager.egate.EgateQueryRunner;
import com.voyager.egate.JMSQueryRunner;
import com.voyager.notification.Notification;
import com.voyager.processor.XMLProcessor;

public class Main {

	public static final String SEP = System.getProperty("file.separator");

	private static Notification notification;
	
	private XMLProcessor xmlProcessor;

	private static Properties properties;

	private static RMSConnection rmsConnection;

	private static RWMSConnection rwmsConnection;

	private static Connection rmsConnectionObject;

	private static Connection rwmsConnectionObject;

	public static FileWriter rmsFileWriter;

	public static FileWriter rwmsFileWriter;

	private RMSQueryRunner rmsQueryRunner;

	private RWMSQueryRunner rwmsQueryRunner;

	private EgateQueryRunner egateQueryRunner;

	private JMSQueryRunner jmsQueryRunner;

	private static HashMap egateProcessResult;

	private static HashMap jmsProcessResult;

	private static HashMap rmsHospitalResult;

	private static String rmsStageResult;

	private static String rwmsHospitalResult;

	private static String ribProcessesReport;

	private static String ribJMSScanReport;

	public static void main(String args[]) throws IOException, SQLException, MessagingException {
		Main main = new Main();

		main.loadProperties();

		String appVersion = properties.getProperty("appVersion");
		if (appVersion.equals("12")) {
			System.out.println("App version is 12");
			//main.delegateWorkFor12();
			main.getAuthToken();
			main.callMessageService();
		} else if (appVersion.equals("13")) {
			// main.delegateWorkFor13();
		}
	}

	private Properties loadProperties() {
		properties = new Properties();
		FileInputStream inputStream;
		try {
			Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("pwd");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            inputStream = new FileInputStream(in.readLine() + "/oramon.properties");
			//inputStream = new FileInputStream("/appl/seebeyond/egtnbs01/RIBMonitor" + "/oramon.properties");
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private void delegateWorkFor12() {
		notification = new Notification();
		xmlProcessor = new XMLProcessor();
		connect(properties);
		process(properties);

		String oramonXML = xmlProcessor.createXML(egateProcessResult, jmsProcessResult, rmsHospitalResult);
		System.out.println("oramonXML is = " + oramonXML);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(properties.getProperty("installDir") + SEP + "oramon.xml")));
			out.write(oramonXML);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			
//			sendNotification(rmsHospitalResult, "RMS");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private void delegateWorkFor13() {

	}

	private void connect(Properties properties) {
		String reportForRMS = properties.getProperty("reportForRMS");
		String reportForRWMS = properties.getProperty("reportForRWMS");

		if (reportForRMS.equalsIgnoreCase("Y")) {
			rmsConnection = new RMSConnection();
			try {
				rmsConnection.setConnection(properties);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (rmsConnection != null) {
				rmsConnectionObject = rmsConnection.getConnection();
			}
		}
		if (reportForRWMS.equalsIgnoreCase("Y")) {
			rwmsConnection = new RWMSConnection();
			try {
				rwmsConnection.setConnection(properties);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (rwmsConnection != null) {
				rwmsConnectionObject = rwmsConnection.getConnection();
			}
		}
	}

	private void process(Properties properties) {
		String reportForEgate = properties.getProperty("reportForEgate");
		String reportForJMS = properties.getProperty("reportForJMS");

		if (rmsConnectionObject != null) {
			rmsQueryRunner = new RMSQueryRunner();
			try {
				rmsHospitalResult = rmsQueryRunner.queryForHospital(rmsConnectionObject, properties);
				//rmsStageResult = rmsQueryRunner.queryForStage(rmsConnectionObject, properties);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		if (rwmsConnectionObject != null) {
			rwmsQueryRunner = new RWMSQueryRunner();
			/*
			 * try { rwmsHospitalResult =
			 * rwmsQueryRunner.queryForHosp(rwmsConnectionObject, properties);
			 * rwmsStageResult =
			 * rwmsQueryRunner.queryForStage(rwmsConnectionObject, properties);
			 * } catch (SQLException e) { e.printStackTrace(); } catch
			 * (MessagingException e) { e.printStackTrace(); }
			 */
		}
		if (reportForEgate.equalsIgnoreCase("Y")) {
			egateQueryRunner = new EgateQueryRunner();

			try {
				System.out.println("Starting the Process report ...");
				egateProcessResult = egateQueryRunner.queryForEgate(properties);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (reportForJMS.equalsIgnoreCase("Y")) {
			jmsQueryRunner = new JMSQueryRunner();

			try {
				jmsProcessResult = jmsQueryRunner.queryForEgate(properties);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void getAuthToken() {
		File file = new File(properties.getProperty("installDir") + SEP + "AuthToken.txt");
		if (file.exists()) {
			System.out.println("File AuthToken.txt exists");
		} else {
			AuthTokenService authToken = new AuthTokenService();
			authToken.getAuthToken(properties);
		}
	}

	public void callMessageService() {
		MessageService service = new MessageService();
		service.sendMessage(properties);
	}

//	private void sendNotification(String hospitalResult, String applicationName) throws IOException {
//		try {
//			notification.sendNotification(properties, null, null, egateProcessResult, jmsProcessResult, applicationName);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String[] hospitalResultArray;
//		int rowCount = 0;
//		try {
//			//hospitalResultArray = hospitalResult.split("\\|");
//			if (Integer.parseInt(hospitalResultArray[1]) != rowCount) {
//				notification.sendNotification(properties, hospitalResultArray[0], rmsStageResult, ribProcessesReport, ribJMSScanReport, applicationName);
//				// mobileSMS.sendSMS(properties, "Records in hospital");
//				System.out.println("Mail Notification for " + applicationName + " hospital has been sent");
//			} else {
//				notification.sendNotification(properties, "No Records in Hospital", rmsStageResult, ribProcessesReport, ribJMSScanReport, applicationName);
//				System.out.println("No Mail Notifications for " + applicationName);
//			}
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
}

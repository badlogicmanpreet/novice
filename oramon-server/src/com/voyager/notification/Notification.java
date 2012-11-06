package com.voyager.notification;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Notification {
	
	public static final String SEP = System.getProperty("file.separator");

	private Session session;

	private MimeMessage mimeMessage;

	private Address[] toAddresses;

	private String recipients;

	private String[] recipientsList;

	private static final String DELIM = "|";

	public void sendNotification(Properties properties, String mailData, String mfqueueData, String ribProcessesReport, String ribJMSScanReport, String applicationName) throws MessagingException, IOException {

		session = Session.getInstance(properties);

		System.out.println("Mail Data = " + mailData);
		System.out.println("========================");
		System.out.println("Process Data = " + ribProcessesReport);
		System.out.println("========================");
		System.out.println("JMS Data = " + ribJMSScanReport);
		System.out.println("========================");
		System.out.println("App name = " + applicationName);
		
		mimeMessage = new MimeMessage(session);
		mimeMessage.setSubject("OM P2A-BIT RIB Report:");
		mimeMessage.setContent("Hello", "text/plain");
		setHTMLContent(properties, mimeMessage, mailData, mfqueueData, ribProcessesReport, ribJMSScanReport);
//
//		 String[] cmd = {"sendmail", "-s OM P2A-BIT RIB Report", "Bryan.Jin@cn.tesco.com"};
//		 Process p = Runtime.getRuntime().exec(cmd);
//		 OutputStreamWriter osw = new OutputStreamWriter(p.getOutputStream());
//		 osw.write(" ");
//		 osw.close();

		mimeMessage.saveChanges();

		recipients = properties.getProperty("toAddress");
		recipientsList = recipients.split("\\|");
		toAddresses = new InternetAddress[recipientsList.length];
		for (int i = 0; i < recipientsList.length; i++) {
			toAddresses[i] = new InternetAddress(recipientsList[i]);
		}
		Address fromAddress = new InternetAddress("RIB-Administrator");

		mimeMessage.setFrom(fromAddress);
		mimeMessage.addRecipients(Message.RecipientType.TO, toAddresses);

		
		//Transport.send(mimeMessage);

	}

	// private List useStringTokenizer(String input)
	// {
	// StringTokenizer st = new StringTokenizer(input, DELIM, true);
	// recipientsList = new ArrayList();
	// while (st.hasMoreTokens())
	// recipientsList.add(extractField(st));
	// System.out.println(recipients);
	// return recipientsList;
	// }
	//	
	// private static String extractField(StringTokenizer st)
	// {
	// String value = st.nextToken();
	// if (DELIM.equals(value))
	// value = "";
	// else if (st.hasMoreTokens())
	// st.nextToken(); // skip next delimiter
	// return value;
	// }

	// class Authentication extends Authenticator {
	// protected PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication("gc06", "Access1234");
	// }
	// }

	// Set a single part html content.
	// Sending data of any type is similar.
	public static void setHTMLContent(Properties properties, Message mimeMessage, String mailData, String mfqueueData, String ribProcessesReport, String ribJMSScanReport) throws MessagingException, IOException {

		String html = "<html><head><title>" + mimeMessage.getSubject() + "</title></head><body><u><h4>" + "Hospital Report" + "</h4></u><p>" + mailData + "<p><u><h4>Staging Table Report</h4></u><p>" + mfqueueData
				+ "<p><u><h4>RIB Processes Report</h4></u><p>" + ribProcessesReport + "<p><u><h4>JMS Scan Report</h4></u><p>" + ribJMSScanReport + "<p>Thanks" + "<br>" + "-RIB Admin</p>" + "</body></html>";

//		FileWriter fstream = new FileWriter(file_name);
//	      BufferedWriter out = new BufferedWriter(fstream);
//	      out.write(in.readLine());
//	      out.close();
//	      System.out.println("File created successfully.");
	    
          File f=new File(properties.getProperty("installDir") + SEP + "RIBReport.html");
	      FileOutputStream fop=new FileOutputStream(f);

	      String content=html;
          fop.write(content.getBytes());


		System.out.println(html);
		// HTMLDataSource is an inner class
		mimeMessage.setDataHandler(new DataHandler(new HTMLDataSource(html)));
	}

	/*
	 * Inner class to act as a JAF datasource to send HTML e-mail content
	 */
	static class HTMLDataSource implements DataSource {
		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		// Return html string in an InputStream.
		// A new stream must be returned each time.
		public InputStream getInputStream() throws IOException {
			if (html == null)
				throw new IOException("Null HTML");
			return new ByteArrayInputStream(html.getBytes());
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		public String getContentType() {
			return "text/html";
		}

		public String getName() {
			return "JAF text/html dataSource to send e-mail only";
		}
	}

	public void notifyRIBStatusReport(Properties properties, File file) {
		try {
			// create a message
			properties.put("mail.smtp.auth", "true");
			Session sess = Session.getInstance(properties, new Auth());

			MimeMessage msg = new MimeMessage(sess);
			msg.setSubject("Eway Status Report");

			msg.setFrom(new InternetAddress("ReportGenerator@in.tesco.com"));
			recipients = properties.getProperty("toAddress");
			recipientsList = recipients.split("\\|");
			toAddresses = new InternetAddress[recipientsList.length];
			for (int i = 0; i < recipientsList.length; i++) {
				toAddresses[i] = new InternetAddress(recipientsList[i]);
			}

			msg.addRecipients(Message.RecipientType.TO, toAddresses);

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(" ");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(file);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}
	}

	class Auth extends Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("gc06", "System@7");
		}
	}

}

package com.voyager.cloud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class MessageService {

	public static final String SEP = System.getProperty("file.separator");
	String regID;
	String authToken;
	Properties properties;

	public void sendMessage(Properties properties) {

		this.properties = properties;
		getRegID();
		getAuthToken();

		Runtime r = Runtime.getRuntime();
		Process p = null;
	
		try {
			System.out.println("Commad is = " + properties.getProperty("installDir") + SEP + "MessageService.sh " + authToken + " " + regID);
			p = r.exec(properties.getProperty("installDir") + SEP + "MessageService.sh " + authToken + " " + regID);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println("" + line.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getRegID() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(properties.getProperty("installDir") + SEP + "RegistrationID.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println("RegID = " + str);
				regID = str;
			}
			in.close();
		} catch (IOException e) {
		}
	}

	public void getAuthToken() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(properties.getProperty("installDir") + SEP + "AuthToken.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println("Auth Token = " + str);
				authToken = str;
			}
			in.close();
		} catch (IOException e) {
		}
	}

}

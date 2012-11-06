package com.voyager.cloud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

//http://situee.blogspot.com/2011/08/c2dm-get-clientlogin-authentication.html

public class AuthTokenService {

	public static final String SEP = System.getProperty("file.separator");

	public void getAuthToken(Properties properties) {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		String authToken = null;

		try {
			p = r.exec(properties.getProperty("installDir") + SEP + "AuthTokenService.sh");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println("" + line.toString());
				if (line.startsWith("Auth=")) {
					authToken = line.substring(5);
					System.out.println("Printing token = " + authToken);
				}
			}

			// Create file
			FileWriter fstream = new FileWriter(properties.getProperty("installDir") + SEP + "AuthToken.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(authToken);

			// Close the output stream
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

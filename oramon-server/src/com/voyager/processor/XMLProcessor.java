package com.voyager.processor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class XMLProcessor {

	public String createXML(HashMap egateProcessResult, HashMap jmsProcessResult, HashMap hospitalResult) {

		System.out.println("Starting to create XML");
		StringBuffer sb = new StringBuffer(500000);
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

		sb.append("<healthReport>");

		// process report
		sb.append("<processReport>");
		sb.append("<processes>");
		if (egateProcessResult != null) {
			// start the loop here for processes
			Collection epCollection = egateProcessResult.keySet();
			Iterator epitr = epCollection.iterator();
			while (epitr.hasNext()) {
				sb.append("<processStat>");
				String key = (String) epitr.next();
				sb.append("<name>");
				sb.append(key);
				sb.append("</name>");
				sb.append("<value>");
				sb.append(egateProcessResult.get(key));
				sb.append("</value>");
				sb.append("</processStat>");
			}
		}
		sb.append("</processes>");
		sb.append("</processReport>");

		// jms report
		sb.append("<jmsReport>");
		sb.append("<topics>");
		if (jmsProcessResult != null) {
			// start the loop here for jms
			Collection jmsCollection = jmsProcessResult.keySet();
			Iterator jmsitr = jmsCollection.iterator();
			while (jmsitr.hasNext()) {
				sb.append("<topicStat>");
				String key = (String) jmsitr.next();
				sb.append("<name>");
				sb.append(key);
				sb.append("</name>");
				sb.append("<count>");
				sb.append(jmsProcessResult.get(key));
				sb.append("</count>");
				sb.append("</topicStat>");
			}
		}
		sb.append("</topics>");
		sb.append("</jmsReport>");

		// hospital report
		sb.append("<hospitalReport>");
		sb.append("<hospital>");
		if (hospitalResult != null) {
			// start the loop here for hospital
			Collection hospCollection = hospitalResult.keySet();
			Iterator hospitr = hospCollection.iterator();
			while (hospitr.hasNext()) {
				sb.append("<hospitalStat>");
				String key = (String) hospitr.next();
				sb.append("<family>");
				sb.append(key);
				sb.append("</family>");
				sb.append("<count>");
				sb.append(hospitalResult.get(key));
				sb.append("</count>");
				sb.append("</hospitalStat>");
			}
		}
		sb.append("</hospital>");
		sb.append("</hospitalReport>");

		sb.append("</healthReport>");

		System.out.println("Starting to create XML");
		return sb.toString();
	}

	/*public static void main(String args[]) {
		String test = new XMLProcessor().createXML(null, null, null);
		System.out.println("Test result is = " + test);
	}*/
}

package com.voyager.cloud;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

public class XMLProcessor {

	String tag = "XMLProcessor";

	String oramonXML;
	int prRowCount;
	int jmsRowCount;
	int hospitalRowCount;
	private static XMLProcessor xmlProcessor = null;

	public DocumentBuilderFactory docBuilderFactory;
	public DocumentBuilder docBuilder = null;
	public Document doc;

	public static XMLProcessor getSingletonObject(String oramonXML) {
		//if (xmlProcessor == null) {
			xmlProcessor = new XMLProcessor(oramonXML);
			return xmlProcessor;
		//} else {
		//	return xmlProcessor;
		//}
	}

	public XMLProcessor(String oramonXML) {
		this.oramonXML = oramonXML;
		parseXML();
	}

	public ArrayList<String> getProcessReport() {
		ArrayList<String> prList = new ArrayList<String>();
		Log.d(tag, "Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("processes");
		Node processNode;
		for (int i = 0; i < nList.getLength(); i++) {
			processNode = nList.item(i);
			NodeList parentNodeList = processNode.getChildNodes();
            prRowCount = parentNodeList.getLength();
			
			for (int index = 0; index < parentNodeList.getLength(); index++) {
				Node node = parentNodeList.item(index);
				NodeList childNodeList = node.getChildNodes();
				for (int iIndex = 0; iIndex < childNodeList.getLength(); iIndex++) {
					Node childNode = childNodeList.item(iIndex);
					String value = childNode.getTextContent();
					Log.d(tag, value);
					prList.add("  " + value);
				}
			}
		}
		return prList;
	}

	public ArrayList<String> getJMSReport() {
		ArrayList<String> jmsList = new ArrayList<String>();
		Log.d(tag, "Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("topics");
		Node processNode;
		for (int i = 0; i < nList.getLength(); i++) {
			processNode = nList.item(i);
			NodeList parentNodeList = processNode.getChildNodes();
            jmsRowCount = parentNodeList.getLength();
			
			for (int index = 0; index < parentNodeList.getLength(); index++) {
				Node node = parentNodeList.item(index);
				NodeList childNodeList = node.getChildNodes();
				for (int iIndex = 0; iIndex < childNodeList.getLength(); iIndex++) {
					Node childNode = childNodeList.item(iIndex);
					String value = childNode.getTextContent();
					Log.d(tag, value);
					jmsList.add("  " + value);
				}
			}
		}
		return jmsList;

	}

	public ArrayList<String> getHospitalReport() {
		ArrayList<String> hospitalList = new ArrayList<String>();
		Log.d(tag, "Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("hospital");
		Node processNode;
		for (int i = 0; i < nList.getLength(); i++) {
			processNode = nList.item(i);
			NodeList parentNodeList = processNode.getChildNodes();
            hospitalRowCount = parentNodeList.getLength();
			
			for (int index = 0; index < parentNodeList.getLength(); index++) {
				Node node = parentNodeList.item(index);
				NodeList childNodeList = node.getChildNodes();
				for (int iIndex = 0; iIndex < childNodeList.getLength(); iIndex++) {
					Node childNode = childNodeList.item(iIndex);
					String value = childNode.getTextContent();
					Log.d(tag, value);
					hospitalList.add("          " + value);
				}
			}
		}
		return hospitalList;
	}

	public int getPRRowCount() {
		return prRowCount;
	}

	public int getJMSRowCount() {
		return jmsRowCount;
	}

	public int getHospitalRowCount() {
		return hospitalRowCount;
	}

	public void parseXML() {
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();

			StringReader sr = new StringReader((String) oramonXML);
			InputSource iSrc = new InputSource(sr);
			doc = docBuilder.parse(iSrc);
			doc.getDocumentElement().normalize();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

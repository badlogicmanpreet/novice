package com.javageek.whereami;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLHandler extends DefaultHandler {

	Boolean currentElement = false;
	String currentValue = "";
	Boolean location = false;
	private XMLMaster xmlMaster = null;
	private ArrayList<XMLMaster> xmlMasterList = new ArrayList<XMLMaster>();

	public ArrayList<XMLMaster> getXMLMasterList() {
		return xmlMasterList;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElement = true;
		if (localName.equals("geometry")) {
			xmlMaster = new XMLMaster();
		}
		if (localName.equals("location")) {
			location = true;
		}

	}

	// Called when tag closing
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		currentElement = false;

		/** set value */
		if (location) {
			if (localName.equalsIgnoreCase("lat")) {
				xmlMaster.setLAT(Double.parseDouble(String.valueOf(currentValue)));
			} else if (localName.equalsIgnoreCase("lng")) {
				xmlMaster.setLNG(Double.parseDouble(String.valueOf(currentValue)));
				location = false;
			}
		}

		if (localName.equalsIgnoreCase("geometry")) {
			xmlMasterList.add(xmlMaster);
		}

	}

	// Called to get tag characters
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		if (currentElement) {
			currentValue = new String(ch, start, length);
		}

	}
}

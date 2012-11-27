package com.javageek.whereami;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyXMLParser {
	
	private String name;
	private String content;
	private Map<String,String> nameAttributes = new HashMap<String,String>();
	private Map<String,ArrayList<MyXMLParser>> nameChildren = new HashMap<String,ArrayList<MyXMLParser>>();
	
	public MyXMLParser(InputStream inputStream, String rootName) {
		this(rootElement(inputStream,rootName));
	}
	
	public MyXMLParser(String filename, String rootName) {
		this(fileInputStream(filename),rootName);
	}
	
	public MyXMLParser(String rootName) {
		this.name = rootName;
	}
	
	private MyXMLParser(Element element) {
		this.name = element.getNodeName();
		this.content = element.getNodeValue();
		NamedNodeMap namedNodeMap = element.getAttributes();
		int n = namedNodeMap.getLength();
		for(int i=0;i<n;i++) {
			Node node = namedNodeMap.item(i);
			String name = node.getNodeName();
    		addAttribute(name,node.getNodeValue());
		}		
		NodeList nodes = element.getChildNodes();
		n = nodes.getLength();
	    for(int i=0;i<n;i++) {
	    	Node node = nodes.item(i);
	    	int type = node.getNodeType();
	    	if(type==Node.ELEMENT_NODE) {
		    	MyXMLParser child = new MyXMLParser((Element)node);
	    		addChild(node.getNodeName(),child);
	    	}
	    	
	    }
	}
	
	public void addAttribute(String name, String value) {
		nameAttributes.put(name,value);
	}
	
	private void addChild(String name, MyXMLParser child) {
		ArrayList<MyXMLParser> children = nameChildren.get(name);
		if(children==null) {
			children = new ArrayList<MyXMLParser>();
			nameChildren.put(name,children);
		}
		children.add(child);
	}
	
	public String name() {
		return name;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String content() {
		return content;
	}
	
	public void addChild(MyXMLParser MyXMLParser) {
		addChild(MyXMLParser.name(),MyXMLParser);
	}
	
	public void addChildren(MyXMLParser... MyXMLParsers) {
		for(MyXMLParser MyXMLParser:MyXMLParsers) addChild(MyXMLParser.name(),MyXMLParser);
	}
	
	public MyXMLParser child(String name) {
		MyXMLParser child = optChild(name);
		if(child==null) throw new RuntimeException("Could not find child node: "+name);
		return child;
	}
	
	public MyXMLParser optChild(String name) {
		ArrayList<MyXMLParser> children = children(name);
		int n = children.size();
		if(n>1) throw new RuntimeException("Could not find individual child node: "+name);
		return n==0 ? null : children.get(0);
	}
	
	public boolean option(String name) {
		return optChild(name)!=null;
	}
	
	public ArrayList<MyXMLParser> children(String name) {
		ArrayList<MyXMLParser> children = nameChildren.get(name);
		return children==null ? new ArrayList<MyXMLParser>() : children;			
	}
	
	public String string(String name) {
		String value = optString(name);
		if(value==null) {
			throw new RuntimeException(
				"Could not find attribute: "+name+", in node: "+this.name);
		}
		return value;
	}
	
	public String optString(String name) {
		return nameAttributes.get(name);
	}
	
	public int integer(String name) {
		return Integer.parseInt(string(name)); 
	}
	
	public Integer optInteger(String name) {
		String string = optString(name);
		return string==null ? null : integer(name); 
	}
	
	public double doubleValue(String name) {
		return Double.parseDouble(optString(name));
	}
	
	public Double optDouble(String name) {
		String string = optString(name);
		return string==null ? null : doubleValue(name);
	}
	
	private static Element rootElement(InputStream inputStream, String rootName) {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
		    Document document = builder.parse(inputStream);
		    Element rootElement = document.getDocumentElement();
		    if(!rootElement.getNodeName().equals(rootName)) 
		    	throw new RuntimeException("Could not find root node: "+rootName);
		    return rootElement;
		}
		catch(IOException exception) {
			throw new RuntimeException(exception);
		}
		catch(ParserConfigurationException exception) {
			throw new RuntimeException(exception);
		}
		catch(SAXException exception) {
			throw new RuntimeException(exception);
		}
		finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				}
				catch(Exception exception) {
					throw new RuntimeException(exception);
				}
			}
		}
	}
	
	private static FileInputStream fileInputStream(String filename) {
		try {
			return new FileInputStream(filename);
		}
		catch(IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
}
package com.converter.xmltojson;

import javax.xml.stream.XMLStreamException;

public interface XMLJSONConverterI {
	public void convertJSONtoXML(String inputJson, String outputXML) throws XMLStreamException;
}

package com.converter.xmltojson;

public class ConverterFactory {
	
	public XMLJSONConverterI getXmlJsonConverter(){
		return new XMLJSONConverter();
	}

}

package com.converter;

import javax.xml.stream.XMLStreamException;

import com.converter.xmltojson.ConverterFactory;
import com.converter.xmltojson.XMLJSONConverterI;

public class ConverterApplication {

	public static void main(String[] args) throws XMLStreamException {
		
		if(args.length != 2){
			System.out.println("Invalid commandline , expecting exactly two input arguments");
			System.exit(1);
		}
		System.out.println("Starting conversion from JSON to XML");
		ConverterFactory converterFactory = new ConverterFactory();
		XMLJSONConverterI converter = converterFactory.getXmlJsonConverter();
		converter.convertJSONtoXML(args[0], args[1]);
		System.out.println("Conversion completed");
	}

}

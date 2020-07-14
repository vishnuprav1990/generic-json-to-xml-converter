package com.converter.xmltojson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.xml.stream.XMLStreamException;

import com.converter.util.ConverterUtil;


public class XMLJSONConverter implements XMLJSONConverterI {

	@Override
	public void convertJSONtoXML(String inputJson, String outputXML) throws XMLStreamException {

		try (FileInputStream inputjsonStream = new FileInputStream(inputJson);
				FileWriter fileWriter = new FileWriter(outputXML);
				PrintWriter printWriter = new PrintWriter(fileWriter);
				Reader reader = new BufferedReader(new InputStreamReader(inputjsonStream))) {
			new ConverterUtil(reader,
					new BufferedWriter(printWriter))
							.convert();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}

}

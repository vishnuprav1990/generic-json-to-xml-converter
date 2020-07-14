package com.converter.util;

import java.io.Reader;
import java.io.Writer;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class ConverterUtil {

	public static final String REPLACEMENT_CHAR = "\uFFFD";
	private static final XMLOutputFactory XOF = XMLOutputFactory.newInstance();

	static {
		XOF.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
	}

	private final JsonParser parser;
	private final XMLStreamWriter writer;

	public ConverterUtil(Reader reader, Writer stream) throws XMLStreamException {
		this(Json.createParser(reader), getXMLOutputFactory().createXMLStreamWriter(stream));
	}

	public ConverterUtil(JsonParser parser, XMLStreamWriter writer) {
		this.parser = parser;
		this.writer = writer;
	}

	public void convert() throws XMLStreamException {
		convert(getParser(), getWriter());
	}

	public static void convert(JsonParser parser, XMLStreamWriter writer) throws XMLStreamException {
		write(parser, writer);
		writer.writeEndDocument();
		writer.flush();
		parser.close();
	}

	public static void write(JsonParser parser, XMLStreamWriter writer) throws XMLStreamException {
		String keyName = null;
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();

			switch (event) {
			case START_ARRAY:
				writer.writeStartElement("array");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				break;
			case END_ARRAY:
				writer.writeEndElement();
				break;
			case START_OBJECT:
				writer.writeStartElement("object");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				break;
			case END_OBJECT:
				writer.writeEndElement();
				break;
			case VALUE_FALSE:
				writer.writeStartElement("boolean");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				writer.writeCharacters("false");
				writer.writeEndElement();
				break;
			case VALUE_TRUE:
				writer.writeStartElement("boolean");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				writer.writeCharacters("true");
				writer.writeEndElement();
				break;
			case KEY_NAME:
				keyName = stripInvalidXMLChars(parser.getString(), REPLACEMENT_CHAR);
				break;
			case VALUE_STRING:
				writer.writeStartElement("string");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				writer.writeCharacters(stripInvalidXMLChars(parser.getString(), REPLACEMENT_CHAR));
				writer.writeEndElement();
				break;
			case VALUE_NUMBER:
				writer.writeStartElement("number");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				writer.writeCharacters(stripInvalidXMLChars(parser.getString(), REPLACEMENT_CHAR));
				writer.writeEndElement();
				break;
			case VALUE_NULL:
				writer.writeEmptyElement("null");
				if (keyName != null) {
					writer.writeAttribute("name", keyName);
					keyName = null;
				}
				break;
			}

			writer.flush();
		}
	}

	public static String stripInvalidXMLChars(String text, String replacement) {
		if (null == text || text.isEmpty())
			return text;

		final int len = text.length();
		char current = 0;
		int codePoint = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			current = text.charAt(i);
			boolean surrogate = false;
			if (Character.isHighSurrogate(current) && i + 1 < len && Character.isLowSurrogate(text.charAt(i + 1))) {
				surrogate = true;
				codePoint = text.codePointAt(i++);
			} else
				codePoint = current;

			if ((codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
					|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
					|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
					|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF))) {
				sb.append(current);
				if (surrogate)
					sb.append(text.charAt(i));
			} else
				sb.append(replacement);
		}

		return sb.toString();
	}

	private JsonParser getParser() {
		return parser;
	}

	private XMLStreamWriter getWriter() {
		return writer;
	}

	private static XMLOutputFactory getXMLOutputFactory() {
		return XOF;
	}

}

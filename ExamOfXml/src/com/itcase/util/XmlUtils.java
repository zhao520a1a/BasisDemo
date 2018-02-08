package com.itcase.util;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlUtils {
	private static String filename = "src/exam.xml";
	
	// 得到代表要解析的xml文档的document
	public static Document getDocument() throws Exception {
		DocumentBuilderFactory foctory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = foctory.newDocumentBuilder();
		Document document = builder.parse(filename);
		return document;
	}

	// 将更新写回硬盘
	public static void writeBack(Document document) throws Exception  {
		TransformerFactory tffactory = TransformerFactory.newInstance();
		Transformer tf = tffactory.newTransformer();
		tf.transform(new DOMSource(document), new StreamResult(Files.newOutputStream(Paths.get(filename))));
	}

}

import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class StudentJAXP2 {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File("booklist.xml"));
		NodeList names = doc.getElementsByTagName("name");
		if (names != null && names.getLength() > 0) {
			Node name = names.item(0);
			name.setTextContent("Struts 2.1开发实战");
		}
		
		DOMImplementationRegistry registry = DOMImplementationRegistry
				.newInstance();
		DOMImplementationLS domImplLS = (DOMImplementationLS) registry
				.getDOMImplementation("LS");
		LSSerializer serializer = domImplLS.createLSSerializer();
		serializer.getDomConfig().setParameter("format-pretty-print", true);
		LSOutput out = domImplLS.createLSOutput();
		out.setEncoding("UTF-8");
		FileWriter stringOut = new FileWriter("booklist.xml");
		out.setCharacterStream(stringOut);
		serializer.write(doc, out);
	}

}

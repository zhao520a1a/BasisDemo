import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentJAXP1 {

	public static void main(String args[]) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder domPaser = factory.newDocumentBuilder();
			Document document = domPaser.parse(new File("student.xml"));
			Element root = document.getDocumentElement();
			NodeList nodelist = root.getElementsByTagName("score");
			int size = nodelist.getLength();
			for (int k = 0; k < size; k++) {
					Element elementNode = (Element) nodelist.item(k);
					Node xmlNode = document.createElement("xml");
					xmlNode.appendChild(document.createTextNode("90"));
					elementNode.appendChild(xmlNode);
			}
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			File file = new File("student.xml");
			FileOutputStream out = new FileOutputStream(file);
			StreamResult xmlResult = new StreamResult(out);
			transformer.transform(domSource, xmlResult);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

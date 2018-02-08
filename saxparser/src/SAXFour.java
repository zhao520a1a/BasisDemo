import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXFour {
	public static void main(String args[]) {
		try {
			File file = new File("example7_4.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXFour handler = new EventHandlerSAXFour();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类
class EventHandlerSAXFour extends DefaultHandler {
	int count = 0;

	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		count++;
		System.out.print("<" + qName + " ");
		for (int k = 0; k < atts.getLength(); k++) {
			System.out.print(atts.getLocalName(k) + "= ");
			System.out.print("\"" + atts.getValue(k) + "\"");
		}
		System.out.println(">");
		if (uri.length() > 0)
			System.out.println("标记隶属的名称空间是" + uri);
	}

	public void endElement(String uri, String localName, String qName) {
		System.out.println("</" + qName + ">");
	}

	public void endDocument() {
		System.out.printf("\n解析过程结束,共有%d个标记", count);
	}
}

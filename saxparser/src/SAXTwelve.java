import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXTwelve {
	public static void main(String args[]) {
		try {
			File file = new File("example7_12.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			factory.setValidating(true);
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXTwelve handler = new EventHandlerSAXTwelve();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class EventHandlerSAXTwelve extends DefaultHandler {
	boolean unparsed = false;

	public void characters(char[] ch, int start, int length) {
		String text = new String(ch, start, length);
		System.out.print(text);
	}

	public void unparsedEntityDecl(String name, String publicId,
			String systemId, String notationName) throws SAXException {
		System.out.println(name);
		System.out.println(publicId);
		System.out.println(systemId);
		System.out.println(notationName);
	}
}

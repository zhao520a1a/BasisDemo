import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXEleven {
	public static void main(String args[]) {
		try {
			File file = new File("example7_1.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXEleven handler = new EventHandlerSAXEleven();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class EventHandlerSAXEleven extends DefaultHandler {
	int rowNum = 0, colNum = 0;
	Locator locator;

	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	public void characters(char[] ch, int start, int length) {
		String text = new String(ch, start, length);
		System.out.print(text);
		rowNum = locator.getLineNumber();
		colNum = locator.getColumnNumber();
		if (text.trim().length() > 0)
			System.out.print("[文本的末尾的位置(" + rowNum + "," + colNum + ")]");
		else if (text.trim().length() == 0)
			System.out.print("[空白类字符的末尾位置(" + rowNum + "," + colNum + ")]");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		System.out.print("<" + qName + ">");
	}

	public void endElement(String uri, String localName, String qName) {
		System.out.println("</" + qName + ">");
	}
}

import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXTen {
	public static void main(String args[]) {
		try {
			File file = new File("example7_9.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXTen handler = new EventHandlerSAXTen();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类
class EventHandlerSAXTen extends DefaultHandler {
	int count = 0;

	public void characters(char[] ch, int start, int length) {
		String text = new String(ch, start, length);
		System.out.print(text);
	}

	public void ignorableWhitespace(char[] ch, int start, int length) {
		count++;
		System.out.print("第" + count + "个空白区");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		System.out.print("<" + localName + ">");
	}

	public void endElement(String uri, String localName, String qName) {
		System.out.print("</" + localName + ">");
	}

	public void endDocument() {
		System.out.println("解析过程结束,报告了" + count + "次空白");
	}
}

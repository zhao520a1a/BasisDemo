import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXOne {
	public static void main(String args[]) {
		try {
			File file = new File("example7_1.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXOne handler = new EventHandlerSAXOne(); // 事件处理器
			saxParser.parse(file, handler);
			System.out.println("事件处理器处理了" + handler.count + "个事件");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类
class EventHandlerSAXOne extends DefaultHandler {
	int count = 0;

	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		System.out.print("<" + qName + ">");
		count++;
	}

	public void endElement(String uri, String localName, String qName) {
		System.out.print("</" + qName + ">");
		count++;
	}

	public void characters(char[] ch, int start, int length) {
		String text = new String(ch, start, length);
		System.out.print(text);
		count++;
	}

	public void startDocument() {
		System.out.println("开始解析XML文件");
		count++;
	}

	public void endDocument() {
		System.out.println("解析过程结束");
		count++;
	}
}

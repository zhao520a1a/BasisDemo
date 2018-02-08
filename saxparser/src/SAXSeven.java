import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXSeven {
	public static void main(String args[]) {
		try {
			File file = new File("example7_7.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXSeven handler = new EventHandlerSAXSeven();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类
class EventHandlerSAXSeven extends DefaultHandler {
	int count = 0;

	public InputSource resolveEntity(String publicId, String systemId) {
		count++;
		return null;
	}

	public void characters(char[] ch, int start, int length) {
		String text = new String(ch, start, length);
		System.out.print(text);
	}

	public void endDocument() {
		System.out.println("解析结束,报告了" + count + "次实体事件");
	}
}

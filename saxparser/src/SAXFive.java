import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXFive {
	public static void main(String args[]) {
		try {
			File file = new File("example7_5.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXFive handler = new EventHandlerSAXFive();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类

class EventHandlerSAXFive extends DefaultHandler {
	int textEventCount;

	public void characters(char[] ch, int start, int length) {
		textEventCount++;
		String text = new String(ch, start, length);
		text = text.trim();
		if (text.length() == 0)
			System.out.println("第 " + textEventCount + " 次文本事件处理的文本是空白字符");
		else
			System.out.println("第 " + textEventCount + " 次文本事件处理的文本是\"" + text
					+ "\"");
	}
}

import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXThree {
	public static void main(String args[]) {
		try {
			File file = new File("example7_3.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXThree handler = new EventHandlerSAXThree();
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类
class EventHandlerSAXThree extends DefaultHandler {
	public void processingInstruction(String target, String data) {
		System.out.println("处理指令的目标：" + target);
		System.out.println("处理指令的内容：" + data);
	}
}

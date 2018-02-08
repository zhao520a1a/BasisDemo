import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import java.io.*;

public class SAXTwo {
	public static void main(String args[]) {
		try {
			File file = new File("example7_2.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			EventHandlerSAXTwo handler = new EventHandlerSAXTwo(file);
			saxParser.parse(file, handler);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

// DefaultHandler是org.xml.sax.helpers包中的类
class EventHandlerSAXTwo extends DefaultHandler {
	File file;
	long timeStart = 0, timeEnd = 0;

	public EventHandlerSAXTwo(File f) {
		file = f;
	}

	public void startDocument() {
		timeStart = System.currentTimeMillis();
		System.out.println("开始解析XML文件");
		System.out.println("文件所在位置：" + file.getAbsolutePath());
		System.out.println("文件长度：" + file.length());
	}

	public void endDocument() {
		timeEnd = System.currentTimeMillis();
		System.out.println("解析过程结束");
		System.out.println("所用时间：" + (timeEnd - timeStart) + "毫秒");
	}
}

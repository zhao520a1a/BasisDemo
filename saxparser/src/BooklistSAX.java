import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class BooklistSAX {
	public static void main(String[] args) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse("booklist.xml", new MyBooklistHandler());
	}
}

class MyBooklistHandler extends DefaultHandler {
	private String currentTag;

	public void characters(char[] ch, int start, int length) {
		if (currentTag.equals("name")) {
			String content = new String(ch, start, length);
			if (content.trim().length() > 0) {
				System.out.println(content.trim());
			}
		}
	}
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		currentTag = qName;
	}
}

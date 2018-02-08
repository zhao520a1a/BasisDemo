import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by golden on 2017/6/7 0007.
 */
public class OrderSAX {
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse("orderlist.xml", new MyOrderHandler());
    }
}

class MyOrderHandler extends DefaultHandler {
    private String currentTag;
    private String name;

    public void characters(char[] ch, int start, int length) {

        if (currentTag.equals("name")) {
            String content = new String(ch, start, length);
            if (content.trim().length() > 0) {
                name = content.trim();
            }
        }
        if (currentTag.equals("number")) {
            String content = new String(ch, start, length);
            if (content.trim().length() > 0) {
                if (Integer.parseInt(content) > 8) {
                    System.out.println(name);
                }
            }
        }
    }


    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {
//        System.out.print("<" + qName + ">");
        currentTag = qName;
    }

    public void endElement(String uri, String localName, String qName) {
//        System.out.print("</" + qName + ">");

    }


    public void startDocument() {
//        System.out.println("开始解析XML文件");
    }

    public void endDocument() {
//        System.out.println("解析过程结束");
    }


}


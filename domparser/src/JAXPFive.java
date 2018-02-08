import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

class GiveDataJAXPFive {
	double average = 0, m = 0;
	String mess;

	public void output(NodeList nodeList) {
		int size = nodeList.getLength();
		for (int k = 0; k < size; k++) {
			Node node = nodeList.item(k);
			if (node.getNodeType() == Node.TEXT_NODE) {
				Text textNode = (Text) node;
				String content = textNode.getWholeText();
				System.out.print(content);
				Element parent = (Element) textNode.getParentNode();
				boolean boo = (parent.getNodeName()).equals("价格");
				if (boo == true) {
					content = textNode.getWholeText();
					average = average + Double.parseDouble(content.trim());
					m++;
				}
			}
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elementNode = (Element) node;
				String name = elementNode.getNodeName();
				String id = elementNode.getAttribute("单位");
				if (id.length() > 0) {
					System.out.print(name + "(" + id + ")：");
					mess = id;
				}
				NodeList nodes = elementNode.getChildNodes();
				output(nodes);
			}
		}
	}
}

public class JAXPFive {
	public static void main(String args[]) {
		GiveDataJAXPFive give = new GiveDataJAXPFive();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder domPaser = factory.newDocumentBuilder();
			Document document = domPaser.parse(new File("example6_5.xml"));
			NodeList nodeList = document.getChildNodes();
			give.output(nodeList);
			System.out.println("平均价格：" + give.average / give.m + give.mess);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

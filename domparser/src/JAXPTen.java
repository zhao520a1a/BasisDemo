import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class JAXPTen {
	public static void main(String args[]) {
		try {
			// 写xml文件，写入信息放到student.xml
			// 每次运行该例程，需要刷新一次student.xml文件
			String[] studentName = { "小刚", "小海", "小明" };
			String[] studentNumber = { "201001", "201002", "201003" };
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder domPaser = factory.newDocumentBuilder();
			Document document = domPaser.newDocument();
			document.setXmlVersion("1.0");
			Element root = document.createElement("学生列表");
			document.appendChild(root);
			for (int k = 1; k <= studentName.length; k++) {
				Node node = document.createElement("学生");
				root.appendChild(node);
			}
			NodeList nodeList = document.getElementsByTagName("学生");
			int size = nodeList.getLength();
			for (int k = 0; k < size; k++) {
				Node node = nodeList.item(k);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elementNode = (Element) node;
					Node nodeName = document.createElement("姓名");
					Node nodeNumber = document.createElement("学号");
					nodeName.appendChild(document
							.createTextNode(studentName[k]));
					nodeNumber.appendChild(document
							.createTextNode(studentNumber[k]));
					elementNode.appendChild(nodeName);
					elementNode.appendChild(nodeNumber);
				}
			}
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			File file = new File("example6_10.xml");
			FileOutputStream out = new FileOutputStream(file);
			StreamResult xmlResult = new StreamResult(out);
			transformer.transform(domSource, xmlResult);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

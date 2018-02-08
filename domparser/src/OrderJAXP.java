import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by golden on 2017/6/8 0008.
 */
public class OrderJAXP {
    public static void main(String args[]) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder domPaser = factory.newDocumentBuilder();
            Document document = domPaser.parse(new File("orderlist.xml"));
            Element root = document.getDocumentElement();


            NodeList nodelist = root.getElementsByTagName("name");
            int size = nodelist.getLength();
            for (int k = 0; k < size; k++) {
                Element elementNode = (Element) nodelist.item(k);
                String str =  elementNode.getTextContent().trim() ;
                Node phoneNode = document.createElement("phone");
                if (str.equals("玩具")) {
                    phoneNode.appendChild(document.createTextNode("10086"));
                }
                if (str.equals("文具")) {
                    elementNode.getNextSibling().getNextSibling().setTextContent("12");

                    phoneNode.appendChild(document.createTextNode("10010"));
                }
                elementNode.getParentNode().appendChild(phoneNode);
            }


            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            File file = new File("orderlist.xml");
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transformer.transform(domSource, xmlResult);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

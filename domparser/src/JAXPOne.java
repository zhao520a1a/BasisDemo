import org.w3c.dom.*; 
import javax.xml.parsers.*;
import java.io.*;
public class JAXPOne{
   public static void main(String args[]){
      try { DocumentBuilderFactory factory=
            DocumentBuilderFactory. newInstance();
            DocumentBuilder domPaser=factory.newDocumentBuilder();
            Document document=domPaser.parse(new File("example6_1.xml")) ;
            String version=document.getXmlVersion();
            System.out.println("XML声明的版本号："+version);
            String encoding=document.getXmlEncoding();
            System.out.println("XML声明的的编码："+encoding); 
            Element root=document.getDocumentElement();
            String rootName=root.getNodeName();
            System.out.println("XML文件根节点的名字："+rootName);  
            NodeList nodelist=root.getElementsByTagName("姓名");
            int size=nodelist.getLength();
            for(int k=0;k<size;k++){
                 Node node=nodelist.item(k);
                 String name=node.getNodeName();
                 String content=node.getTextContent(); 
                 System.out.print(name);
                 System.out.println("："+content);
            }
       }           
       catch(Exception e){
            System.out.println(e);
       }
   }
}

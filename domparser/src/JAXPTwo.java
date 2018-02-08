import org.w3c.dom.*; 
import javax.xml.parsers.*;
import java.io.*;
public class JAXPTwo{
   public static void main(String args[]){
      try{  DocumentBuilderFactory factory=
            DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser= factory.newDocumentBuilder();
            Document document=domParser.parse(new File("example6_2.xml")) ;
            NodeList nodeList=document.getChildNodes();
            output(nodeList);     
      }           
      catch(Exception e){
            System.out.println(e);
      }
   }
   public static void output(NodeList nodeList){  //output是一个递归方法
      int size=nodeList.getLength();
      for(int k=0;k<size;k++){
         Node node=nodeList.item(k);
         if(node.getNodeType()==Node.TEXT_NODE){
             Text textNode=(Text)node;
             String content=textNode.getWholeText();
             System.out.print(content);
         } 
         if(node.getNodeType()==Node.ELEMENT_NODE){
             Element elementNode=(Element)node;
             String name=elementNode.getNodeName();
             System.out.print(name+":"); 
             NodeList nodes=elementNode.getChildNodes();
             output(nodes);
         }
      }
   }
}


import org.w3c.dom.*; 
import javax.xml.parsers.*;
import java.io.*;
public class JAXPSeven{
   public static void main(String args[]){
      try { DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser=factory.newDocumentBuilder();
            Document document=domParser.parse(new File("example6_7.xml")) ;
            DocumentType doctype=document.getDoctype();
            String DTDName=doctype.getName();
            System.out.println("DTD名字："+DTDName); 
            String publicId=doctype.getPublicId();
            System.out.println("public标识："+publicId);
            String systemId=doctype.getSystemId();
            System.out.println("system标识："+systemId);
            String internalDTD =doctype.getInternalSubset();
            System.out.println("内部DTD："+internalDTD);     
          }           
      catch(Exception e){}
   }
}

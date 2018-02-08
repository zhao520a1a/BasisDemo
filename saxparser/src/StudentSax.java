import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class StudentSax  {
	public static void main(String[] args) throws Exception
	{	SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse("student.xml" , new MyHandler()); 
	}

}
  class MyHandler extends DefaultHandler
{          private String currentTag;
            public void characters(char[] ch, int start, int length) 
            {           if(currentTag.equals("Java")||currentTag.equals("Struts")||currentTag.equals("Hibernate"))
                         {           String content = new String(ch , start , length);
	              if (content.trim().length() > 0)
	               {           int score = Integer.parseInt(content);
                                                    if(score>90)
                                                    {            System.out.println("<" + currentTag + ">的分数大于90分"); }
	                }
                           }
             }
             public void startElement(String uri , String localName, String qName , Attributes atts) 
            {           currentTag = qName;
            }
}

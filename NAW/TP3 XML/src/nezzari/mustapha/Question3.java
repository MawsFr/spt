package nezzari.mustapha;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Question3 {
	
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		dbf.setCoalescing(false);
		dbf.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document document = db.parse(new File("expbis.xml"));
		
		Element a = document.getElementById("A");
		a.setAttribute("valeur", "5");
		
		Element b = document.getElementById("B");
		b.setAttribute("valeur", "10");

		Element c = document.getElementById("C");
		c.setAttribute("valeur", "15");
		
		Element exps = (Element) document.getDocumentElement().getFirstChild().getNextSibling();
		
		NodeList children = document.getElementsByTagName("exp");
		
		for(int i = 0; i < children.getLength(); i++) {
			if(((Element) children.item(i)).getAttribute("nom").equals("exp4")) {
				exps.removeChild(children.item(i));
				
				
			}
			
		}
		
		XMLTOOLS1.ecrireXML(document, "question3.xml", "expMath.dtd");
		
		
	}

}

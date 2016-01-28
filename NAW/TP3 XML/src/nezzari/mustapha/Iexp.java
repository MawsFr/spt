package nezzari.mustapha;

import java.io.*;
import org.w3c.dom.*;  
import javax.xml.parsers.*; 

public class Iexp {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		dbf.setCoalescing(false);
		dbf.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document document = db.parse(new File("expMath.xml"));
		
//		//Ajout de l'élément X
//		Element constantes = (Element) document.getDocumentElement().getFirstChild();
//		Element newVar = document.createElement("const");
//		newVar.setAttribute("nom", "X");
//		newVar.setAttribute("valeur", "40");
//		constantes.appendChild(newVar);

		//Ajout de l'expression 4
		Element exps = (Element) document.getDocumentElement().getFirstChild().getNextSibling(); 
		Element exp = document.createElement("exp") ; 
		exp.setAttribute("nom", "exp4"); 
		Element op1 = document.createElement("op"); 
		op1.setAttribute("symbole", "mult");
		Element var1 = document.createElement("var"); 
		var1.setAttribute("nom", "A"); 
		Element op2 = (Element) op1.cloneNode(true); 
		Element var2 = (Element) var1.cloneNode(true);
		Element var3 = (Element) var1.cloneNode(true);
		op1.appendChild(op2); op1.appendChild(var3);  
		op2.appendChild(var1); op2.appendChild(var2);   
		exp.appendChild(op1); 
		exps.appendChild(exp);

		//Ajout de l'expression 5
		Element exp5 = document.createElement("exp");
		exp5.setAttribute("nom", "exp5");
		//première opération
		Element op3 = document.createElement("op");
		op3.setAttribute("symbole", "div");
		Element var4 = (Element) var3.cloneNode(true);
		Element var5 = (Element) var4.cloneNode(true);

		//deuxieme opération
		Element op4 = (Element) op3.cloneNode(true);
		Element var6 = (Element) var4.cloneNode(true);
		Element var7 = (Element) var5.cloneNode(true);

		//opération englobante (moins)
		Element op5 = document.createElement("op");
		op5.setAttribute("symbole", "moins");

		op3.appendChild(var4);
		op3.appendChild(var5);

		op4.appendChild(var6);
		op4.appendChild(var7);

		op5.appendChild(op3);
		op5.appendChild(op4);

		exp5.appendChild(op5);

		exps.appendChild(exp5);

		XMLTOOLS1.ecrireXML(document, "expbis.xml", "expMath.dtd");  
	}

}
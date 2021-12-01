package hu.domparse.U678MF;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;

import java.io.*;

public class DomQueryU678MF {
	public static void queryElements() {
		try {
			 //Initialize a file
	         File inputFile = new File("XMLU678MF.xml");
	         
	         //Create a DocumentBuilder from DocumentBuilderFactory
	         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	         DocumentBuilder db = dbf.newDocumentBuilder();
	         
	         //Create a Document from a file
	         Document doc = db.parse(inputFile);
	         
	         doc.getDocumentElement().normalize();
	         
	         // Create XPath object
	         XPath xPath = XPathFactory.newInstance().newXPath();
	         
	         //-------------------QUERIES-------------------
				
	         // Queries stadiums with a capacity of more than 20 000
	         String query1 = "nba/stadionok/stadion[befogadokepesseg>20000]";
				
	         // Queries the player with jatekosID 4006 
	         String query2 = "nba/jatekosok/jatekos[@jatekosID='4006']";
				
	         // Queries statistics where the number of assists is more than 3000 and less than 5600
	         String query3 = "nba/statisztikak/statisztika[golpassz>3000 and golpassz<5600]";
				
				
	         //--------------------------------------------------
	         
	         // Evaluation of query expression
	         NodeList nodeList = (NodeList) xPath.compile(query1).evaluate(doc, XPathConstants.NODESET);
	         System.out.println("Query result of stadiums with a capacity of more than 20 000\n");
	         printElements(nodeList, "stadionID");
	         
	         System.out.println("\n-------------------------------------------\n");
	         
	         // Evaluation of query expression
	         nodeList = (NodeList) xPath.compile(query2).evaluate(doc, XPathConstants.NODESET);
	         System.out.println("Query result of player with jatekosID 4006 \n");
	         printElements(nodeList, "jatekosID");
	         
	         System.out.println("\n-------------------------------------------\n");
	         
	         // Evaluation of query expression
	         nodeList = (NodeList) xPath.compile(query3).evaluate(doc, XPathConstants.NODESET);
	         System.out.println("Query result of statistics where the number of assists is more than 3000 and less than 5600\n");
	         printElements(nodeList, "statisztikaID");
	         
	      } catch (Exception e) {
	    	 System.out.println("Some error occured\nDescription:\n" + e.getMessage());
	         e.printStackTrace();
	      }
	}
	
	//Method to print the elements of a NodeList formatted
	private static void printElements(NodeList nodeList, String idString) {
		for (int i = 0; i < nodeList.getLength(); i++) {
       	 Node node = nodeList.item(i);
       	 System.out.println("Elem: " + node.getNodeName());
       	 Node nNode = nodeList.item(i);
       	 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
       		 Element elem = (Element)nNode;
					
       		 // Print the identifier
       		 String id = elem.getAttribute(idString);
       		 System.out.println(idString + ": " + id);
					
       		 // Print child nodes
       		 String nodeContent = "";
       		 NodeList childNodes = elem.getChildNodes();
       		 for (int j = 0; j < childNodes.getLength(); j++) {
       			 if (childNodes.item(j).getTextContent().trim() != "") {
       				 nodeContent = formatText(childNodes.item(j).getTextContent().trim());
       				 System.out.println(childNodes.item(j).getNodeName() + ": " + nodeContent);
       			 }	
				}
       	 }
       	 System.out.println();
        }
	}
	
	// Method to format text for printing
	private static String formatText(String text) {
		text = text.replaceAll("\\n", ", ");
		text = text.replaceAll("\\s+", " ");
		return text;
	}
}

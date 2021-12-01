package hu.domparse.U678MF;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DomReadU678MF {
	public static void readElements(){
        try {
        	//Initialize a file
            File file = new File("XMLU678MF.xml");
            
            //Create a DocumentBuilder from DocumentBuilderFactory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            //Create a Document from a file
            Document doc = db.parse(file);
            
            doc.getDocumentElement().normalize();
            
            //Examine attributes and sub-elements of stadion node
            NodeList nodeList = doc.getElementsByTagName("stadion");         
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\n----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("StadionID: "+ eElement.getAttribute("stadionID"));
                    System.out.println("Nev: "+ eElement.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Varos: "+ eElement.getElementsByTagName("varos").item(0).getTextContent());
                    System.out.println("Cim: "+ eElement.getElementsByTagName("cim").item(0).getTextContent());
                    System.out.println("Befogadokepesseg: "+ eElement.getElementsByTagName("befogadokepesseg").item(0).getTextContent());
                    System.out.println("CsapatID: "+ eElement.getElementsByTagName("csapatID").item(0).getTextContent());
                }
            }
            
            //Examine attributes and sub-elements of csapat node
            nodeList = doc.getElementsByTagName("csapat");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\n----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("CsapatID: "+ eElement.getAttribute("csapatID"));
                    System.out.println("Nev: "+ eElement.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Varos: "+ eElement.getElementsByTagName("varos").item(0).getTextContent());
                }
            }
            
            //Examine attributes and sub-elements of meccs node
            nodeList = doc.getElementsByTagName("meccs");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\n----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("MeccsID: "+ eElement.getAttribute("meccsID"));
                    System.out.println("Datum: "+ eElement.getElementsByTagName("datum").item(0).getTextContent());
                    System.out.println("StadionID: "+ eElement.getElementsByTagName("stadionID").item(0).getTextContent());
                }
            }
            
            //Examine attributes and sub-elements of jatszik node
            nodeList = doc.getElementsByTagName("jatszik");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\n----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("Hazai csapat pontszam: "+ eElement.getElementsByTagName("hazaiCsapatPontszam").item(0).getTextContent());
                    System.out.println("Vendeg csapat pontszam: "+ eElement.getElementsByTagName("vendegCsapatPontszam").item(0).getTextContent());
                    System.out.println("CsapatID: "+ eElement.getElementsByTagName("csapatID").item(0).getTextContent());
                    System.out.println("MeccsID: "+ eElement.getElementsByTagName("meccsID").item(0).getTextContent());
                }
            }
            
            //Examine attributes and sub-elements of jatekos node
            nodeList = doc.getElementsByTagName("jatekos");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\n----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("JatekosID: "+ eElement.getAttribute("jatekosID"));
                    System.out.println("Nev: "+ eElement.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Kor: "+ eElement.getElementsByTagName("kor").item(0).getTextContent());
                    System.out.println("Mezszam: "+ eElement.getElementsByTagName("mezszam").item(0).getTextContent());
                    System.out.println("Poszt: "+ eElement.getElementsByTagName("poszt").item(0).getTextContent());
                    System.out.println("CsapatID: "+ eElement.getElementsByTagName("csapatID").item(0).getTextContent());
                }
            }
            
            //Examine attributes and sub-elements of statisztika node
            nodeList = doc.getElementsByTagName("statisztika");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\n----- " + node.getNodeName() + (itr + 1) + " -----");
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    System.out.println("StatisztikaID: "+ eElement.getAttribute("statisztikaID"));
                    System.out.println("Jatszott perc: "+ eElement.getElementsByTagName("jatszottPerc").item(0).getTextContent());
                    System.out.println("Mezonykosar: "+ eElement.getElementsByTagName("mezonykosar").item(0).getTextContent());
                    System.out.println("Harompontos: "+ eElement.getElementsByTagName("harompontos").item(0).getTextContent());
                    System.out.println("Bunteto: "+ eElement.getElementsByTagName("bunteto").item(0).getTextContent());
                    System.out.println("Golpassz: "+ eElement.getElementsByTagName("golpassz").item(0).getTextContent());
                    System.out.println("Lepattano: "+ eElement.getElementsByTagName("lepattano").item(0).getTextContent());
                    System.out.println("JatekosID: "+ eElement.getElementsByTagName("jatekosID").item(0).getTextContent());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

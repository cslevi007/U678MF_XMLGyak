package saxU678MF1019;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

public class SaxU678MF {

	public static void main(String[] args) {
		try{
			/*Dokumentumolvasó létrehozáa  gyár objektumot a SAXParserFactory osztály newInstance metódusával*/
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
			
			//PÉDÁNYOSÍTJA A sax ÉRTELMEZÕT, A VISSZAKAPOTT GYÁR ÁLLÍTJA ELÕ A sax ELEMZÕT
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			//saját eseménykezelõ objektum létrehozása
			SaxHandler hander = new SaxHandler();
		} catch(ParserConfigurationException | SAXException e){
			e.printStackTrace();
		}
	}
}

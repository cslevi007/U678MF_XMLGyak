package saxU678MF1019;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

public class SaxU678MF {

	public static void main(String[] args) {
		try{
			/*Dokumentumolvas� l�trehoz�a  gy�r objektumot a SAXParserFactory oszt�ly newInstance met�dus�val*/
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
			
			//P�D�NYOS�TJA A sax �RTELMEZ�T, A VISSZAKAPOTT GY�R �LL�TJA EL� A sax ELEMZ�T
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			//saj�t esem�nykezel� objektum l�trehoz�sa
			SaxHandler handler = new SaxHandler();
			saxParser.parse(new File("src/saxU678MF1019/szemelyekU678MF.xml"), handler);
		} catch(ParserConfigurationException | SAXException | IOException e){
			e.printStackTrace();
		}
	}
}

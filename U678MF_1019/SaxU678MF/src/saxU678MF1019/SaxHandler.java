package saxU678MF1019;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler{
	private int indent = 0;
	
	private String formatAttributes(Attributes attributes){
		int attrLenght = attributes.getLength();
		if(attrLenght == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder(", {");
		for (int i = 0; i < attrLenght; i++) {
			sb.append(attributes.getLocalName(i) + ":" + attributes.getValue(i));
			if(i < attrLenght -1){
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	private void indent(){
		for(int i=0; i < indent; i++){
			System.out.println("  ");
		}
	}
	
	
	//esem�nykezel� met�dusok l�trehoz�sa, startElement met�dust �jraimplement�ljuk
	//elem kezdete �s v�ge
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes){
		indent++;
		indent();
		System.out.println(qName + formatAttributes(attributes) + " start");
	}
	
	
	//end element met�dust �jraimplement�ljuk
	@Override
	public void endElement(String uri, String localName, String qName){
		indent();
		indent--;
		System.out.println(qName + " end");
	}
	
	
	//sz�vegelem feldolgoz�sa, characters met�dust �jraimplement�ljuk
	@Override
	public void characters(char[] ch, int start, int lenght){
		String chars = new String(ch, start, lenght).trim();
		if(!chars.isEmpty()){
			indent++;
			indent();
			indent--;
			System.out.println(chars);
		}
	}
}

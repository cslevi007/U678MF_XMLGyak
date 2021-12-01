package hu.domparse.U678MF;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomModifyU678MF {
	
	//Instantiate a BufferedReader for read user input
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	//Method to
	//-Create a DocumentBuilder from DocumentBuilderFactory
	//-Create a Document from a file
	//-Do the updating of elements as long as the user wants
	//-Call the printing method in the end to print out the results and save it to a new file
	public static void modifyElements() {		
		try {
			DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(new File("XMLU678MF.xml"));
			
			Document modifiedDocument = dom;
			boolean yes = true;
			
			do {
				modifiedDocument = update(modifiedDocument);
				System.out.println("Szeretnel további elemeket modositani?\nHa igen, akkor nyomj 1-est, ha nem akkor bármi mást.");
				if(reader.readLine().compareTo("1") != 0) {
					yes = false;
				}
			} while(yes);
			
			print(modifiedDocument);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (Exception ioe) {
			System.err.println("kuuutya");
			ioe.printStackTrace();
		}
	}
	
	//Method to do the actual updating of an element based on user-specified parameters
	public static Document update(Document dom) {
		Node rootNode = dom.getDocumentElement();
		Node innerNode = null;
		Node inner2Node = null;
		Node inner3Node = null;
		
		List<String> parameters = null;
		boolean ok = false;
		
		while(!ok) {
			try {
				parameters = getDataFromUser();
				ok = true;
			} catch (Exception e) {
				System.out.println("Hibás azonosito! Fussunk neki megegyszer!\n");
			}
		}
		
			
			for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {
				innerNode = rootNode.getChildNodes().item(i);
				
				if(innerNode.getNodeType() == Node.ELEMENT_NODE) {
					for (int j = 0; j < innerNode.getChildNodes().getLength(); j++) {
						inner2Node = innerNode.getChildNodes().item(j);
						
						if(inner2Node.getNodeType() == Node.ELEMENT_NODE) {
							Element e1 = (Element) inner2Node;
							
							if(parameters.get(0).compareTo(inner2Node.getNodeName()) == 0) {
								if(parameters.get(2).compareTo(e1.getAttribute(parameters.get(1))) == 0) {
									for (int k = 0; k < e1.getChildNodes().getLength(); k++) {
										inner3Node = e1.getChildNodes().item(k);
										
										if(inner3Node.getNodeType() == Node.ELEMENT_NODE) {
											Element e2 = (Element) inner3Node;
											
											if(e2.getNodeName().compareTo(parameters.get(3)) == 0) {
												e2.setTextContent(parameters.get(4));
											}
										}
									}
								}
							}
						}
					}
				}			
			} 
			
		return dom;
	}
	
	//Method to get data from user 
	public static List<String> getDataFromUser() throws IOException {
		List<String> resultList = new ArrayList<String>();
		
		System.out.println("Add meg a kódját, hogy melyik node-on szeretnél módosítani:");
		System.out.println("stadion - 1\ncsapat - 2\njatekos - 3\nstatisztika - 4");
		
		//Reading data from user
		String codeString = reader.readLine();
		
		if(codeString.compareTo("1") == 0 || codeString.compareTo("2") == 0 || codeString.compareTo("3") == 0 || codeString.compareTo("4") == 0) {
			
			List<String> x = null;
			boolean ok = false;
			
			switch (codeString) {
			case "1":
				resultList.add("stadion");
				resultList.add("stadionID");
				
				while(!ok) {
					try {
						x = stadionMethod();
						ok = true;
					} catch (IllegalArgumentException e) {
						System.out.println("Nem ervenyes adat! Lássuk csak mégegyszer!\n");
					} catch (IOException e) {
						System.out.println("Nem letezo stadionID! Add meg újra!");
					}
				}
				
				resultList.add(x.get(0));
				resultList.add(x.get(1));
				resultList.add(x.get(2));
				break;
			case "2":
				System.out.println("kkkkiskutya");
				resultList.add("csapat");
				resultList.add("csapatID");
				
				while(!ok) {
					try {
						x = csapatMethod();
						ok = true;
					} catch (IllegalArgumentException e) {
						System.out.println("Nem ervenyes adat! Lássuk csak mégegyszer!\n");
					} catch (IOException e) {
						System.out.println("Nem letezo csapatID! Add meg újra!");
					}
				}
				
				resultList.add(x.get(0));
				resultList.add(x.get(1));
				resultList.add(x.get(2));
				break;
			case "3":
				resultList.add("jatekos");
				resultList.add("jatekosID");
				
				while(!ok) {
					try {
						x = jatekosMethod();
						ok = true;
					} catch (IllegalArgumentException e) {
						System.out.println("Nem ervenyes adat! Lássuk csak mégegyszer!\n");
					} catch (IOException e) {
						System.out.println("Nem letezo jatekosID! Add meg újra!");
					}
				}
				
				resultList.add(x.get(0));
				resultList.add(x.get(1));
				resultList.add(x.get(2));
				break;
			case "4":
				resultList.add("statisztika");
				resultList.add("statisztikaID");
				
				while(!ok) {
					try {
						x = statisztikaMethod();
						ok = true;
					} catch (IllegalArgumentException e) {
						System.out.println("Nem ervenyes adat! Lássuk csak mégegyszer!\n");
					} catch (IOException e) {
						System.out.println("Nem letezo statisztikaID! Add meg újra!");
					}
				}
				
				resultList.add(x.get(0));
				resultList.add(x.get(1));
				resultList.add(x.get(2));
				break;
			}
		} else {
			throw new IllegalArgumentException("Unexpected value: " + codeString);
		}
		
		return resultList;
	}
	
	//Method to handle stadion node modifications
	private static List<String> stadionMethod() throws IOException {
		List<String> resultList = new ArrayList<String>();
		
		
		System.out.println("Add meg a módosítani kívánt Stadion ID-ját:");
		String idString = reader.readLine();
		
		if(Integer.parseInt(idString) < 1000 || Integer.parseInt(idString) > 1007) {
			throw new IOException();
		} else {
			resultList.add(idString);
		}
		
		System.out.println("Add meg a kódját, hogy a StadionID: " + idString +  " stadion melyik node-ján szeretnél módosítani:");
		System.out.println("nev - 1\nbefogadokepesseg - 2\ncsapatID - 3");
		
		String code = reader.readLine();
		
		if(code.compareTo("1") == 0) {
			resultList.add("nev");
			System.out.println("Add meg a stadion új nevét: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("2") == 0) {
			resultList.add("befogadokepesseg");
			System.out.println("Add meg a stadion új befogadokepesseget: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("3") == 0) {
			resultList.add("csapatID");
			System.out.println("Add meg a stadion új csapatID-ját: ");
			resultList.add(reader.readLine());
			
		} else {
			throw new IllegalArgumentException("Unexpected value: " + code);
		}
		
		return resultList;
	}
	
	//Method to handle csapat node modifications
	private static List<String> csapatMethod() throws IOException {
		List<String> resultList = new ArrayList<String>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Add meg a módosítani kívánt Csapat ID-ját:");
		String idString = reader.readLine();
		
		if(Integer.parseInt(idString) < 2000 || Integer.parseInt(idString) > 2007) {
			throw new IOException();
		} else {
			resultList.add(idString);
		}
		
		System.out.println("Add meg a kódját, hogy a CsapatID: " + idString +  " csapat melyik node-ján szeretnél módosítani:");
		System.out.println("nev - 1\nvaros - 2");
		
		String code = reader.readLine();
		
		if(code.compareTo("1") == 0) {
			resultList.add("nev");
			System.out.println("Add meg a csapat új nevét: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("2") == 0) {
			resultList.add("varos");
			System.out.println("Add meg a csapat új városát: ");
			resultList.add(reader.readLine());
			
		} else {
			throw new IllegalArgumentException("Unexpected value: " + code);
		}
		
		return resultList;
	}
	
	//Method to handle jatekos node modifications
	private static List<String> jatekosMethod() throws IOException {
		List<String> resultList = new ArrayList<String>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Add meg a módosítani kívánt Jatekos ID-ját:");
		String idString = reader.readLine();
		
		if(Integer.parseInt(idString) < 4000 || Integer.parseInt(idString) > 4007) {
			throw new IOException();
		} else {
			resultList.add(idString);
		}
		
		System.out.println("Add meg a kódját, hogy a JatekosID: " + idString +  " jatekos melyik node-ján szeretnél módosítani:");
		System.out.println("nev - 1\nmezszam - 2\nposzt - 3\ncsapatid - 4");
		
		String code = reader.readLine();
		
		if(code.compareTo("1") == 0) {
			resultList.add("nev");
			System.out.println("Add meg a jatekos új nevét: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("2") == 0) {
			resultList.add("mezszam");
			System.out.println("Add meg a jatekos új mezszamat: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("3") == 0) {
			resultList.add("poszt");
			System.out.println("Add meg a jatekos új posztjat: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("4") == 0) {
			resultList.add("csapatID");
			System.out.println("Add meg a jatekos új csapatID-jat: ");
			resultList.add(reader.readLine());
			
		} else {
			throw new IllegalArgumentException("Unexpected value: " + code);
		}
		
		return resultList;
	}
	
	//Method to handle statisztika node modifications
	private static List<String> statisztikaMethod() throws IOException {
		List<String> resultList = new ArrayList<String>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Add meg a módosítani kívánt Statisztika ID-ját:");
		String idString = reader.readLine();
		
		if(Integer.parseInt(idString) < 5000 || Integer.parseInt(idString) > 5007) {
			throw new IOException();
		} else {
			resultList.add(idString);
		}
		
		System.out.println("Add meg a kódját, hogy a StatisztikaID: " + idString +  " statisztika melyik node-ján szeretnél módosítani:");
		System.out.println("mezonykosar - 1\nharompontos - 2\nbunteto - 3\ncgolpassz - 4\nlepattano - 5");
		
		String code = reader.readLine();
		
		if(code.compareTo("1") == 0) {
			resultList.add("mezonykosar");
			System.out.println("Add meg a jatekos statisztika új mezonykosarainak szamat: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("2") == 0) {
			resultList.add("harompontos");
			System.out.println("Add meg a jatekos statisztika új harompontosainak szamat: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("3") == 0) {
			resultList.add("bunteto");
			System.out.println("Add meg a jatekos statisztika új buntetoinek szamat: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("4") == 0) {
			resultList.add("golpassz");
			System.out.println("Add meg a jatekos statisztika új golpasszainak szamat: ");
			resultList.add(reader.readLine());
			
		} else if (code.compareTo("5") == 0) {
			resultList.add("lepattano");
			System.out.println("Add meg a jatekos statisztika új lepattanoinak szamat: ");
			resultList.add(reader.readLine());
			
		} else {
			throw new IllegalArgumentException("Unexpected value: " + code);
		}
		
		return resultList;
	}
	
	//Method to print the modified xml to the console and save it to a new file
	public static void print(Document doc) {		
		try {       
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("modifiedXml.xml"));
            
            transformer.transform(domSource, console);
            transformer.transform(domSource, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

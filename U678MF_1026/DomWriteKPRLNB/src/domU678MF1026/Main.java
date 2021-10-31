package domU678MF1026;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {

	public static void main(String[] args) {
		DomWriteU678MF domWriteU678MF = new DomWriteU678MF();
		try {
			domWriteU678MF.write();
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

}

package eg.edu.alexu.csd.filestructure.btree;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SearchEngine implements ISearchEngine {

	@Override
	public void indexWebPage(String filePath) {
		File file = new File(filePath); 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}  

	}

	@Override
	public void indexDirectory(String directoryPath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWebPage(String filePath) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ISearchResult> searchByWordWithRanking(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ISearchResult> searchByMultipleWordWithRanking(String sentence) {
		// TODO Auto-generated method stub
		return null;
	}

}

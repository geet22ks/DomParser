import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParseUser {

	public static HashMap<Integer, String> Call() {
		HashMap<Integer, String> users = new HashMap<Integer, String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		int id = 0;
		try {
			DocumentBuilder Builder = factory.newDocumentBuilder();
			Document doc = Builder.parse("users.xml");
			NodeList list = doc.getElementsByTagName("row");

			for (int i = 0; i < list.getLength(); i++) {
				Node p = list.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element names = (Element) p;
					String ownerId = names.getAttribute("Id");
					String name = names.getAttribute("DisplayName");
					if (ownerId != null && ownerId != "") {
						id = Integer.parseInt(ownerId);
					}
					if (!users.containsKey(id)) {
						users.put(id, name);
					}

				}

			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;

	}
}

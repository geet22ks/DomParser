import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Domparse {

	public static void main(String[] args) {
		HashMap<Integer, Integer> questions = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> answers = new HashMap<Integer, Integer>();
		ArrayList QuestionId = new ArrayList();
		ArrayList AnswerId = new ArrayList();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		int id = 0;
		try {
			DocumentBuilder Builder = factory.newDocumentBuilder();
			Document doc = Builder.parse("posts.xml");
			NodeList list = doc.getElementsByTagName("row");

			for (int i = 0; i < list.getLength(); i++) {
				Node p = list.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element postsCnt = (Element) p;
					String postTypeId = postsCnt.getAttribute("PostTypeId");
					String ownerId = postsCnt.getAttribute("OwnerUserId");
					// map.put(ownerId, id);
					if (ownerId != null && ownerId != "") {
						id = Integer.parseInt(ownerId);
					}
					if (postTypeId.equals("1")) {
						Integer cnt = questions.get(id);
						if (cnt == null) {
							cnt = 0;
						}
						questions.put(id, cnt + 1);
					}
					if (postTypeId.equals("2")) {
						Integer cnt = answers.get(id);
						if (cnt == null) {
							cnt = 0;

						}
						answers.put(id, cnt + 1);
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

		HashMap<Integer, Integer> sortedMapQuestions = sortByValue(questions);
		HashMap<Integer, Integer> sortedMapAnswers = sortByValue(answers);

		int i = 0;
		int j = 0;
		for (Entry<Integer, Integer> entry : sortedMapQuestions.entrySet()) {

			Integer key = entry.getKey();
			Integer value4 = entry.getValue();
			QuestionId.add(key);
			// System.out.println("key, " + key + " value " + value4 );
			i++;
			if (i == 10)
				break;
		}
		i = 0;
		for (Entry<Integer, Integer> entry : sortedMapAnswers.entrySet()) {

			Integer key = entry.getKey();
			Integer value4 = entry.getValue();
			AnswerId.add(key);
			// System.out.println("key, " + key + " value " + value4 );
			i++;
			if (i == 10)
				break;
		}
		System.out.println("Top 10 users for questions");
		Displaynames(QuestionId);
		System.out.println("\n\n\nTop 10 users for answers");
		Displaynames(AnswerId);

	}

	private static void Displaynames(ArrayList list) {

		DomParseUser dom = new DomParseUser();
		HashMap<Integer, String> TmpMap = new HashMap<Integer, String>();

		TmpMap = dom.Call();
		for (Entry<Integer, String> entry : TmpMap.entrySet()) {

			Integer key = entry.getKey();
			String value4 = entry.getValue();
		}
		int c = 0;
		for (Entry<Integer, String> entry : TmpMap.entrySet()) {
			for (int i = 0; i < 10; i++) {
				Integer key = entry.getKey();
				String value4 = entry.getValue();
				if (list.get(i).equals(key)) {
					{
						System.out.println(c + ":  User id:" + key + " Name:"
								+ value4);
						c++;
					}

				}
				// TODO Auto-generated method stub
			}
		}
	}

	private static HashMap<Integer, Integer> sortByValue(
			Map<Integer, Integer> unsortMap) {

		LinkedList<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(
				unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1,
					Map.Entry<Integer, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// Convert sorted map back to a Map
		HashMap<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
		for (Iterator<Map.Entry<Integer, Integer>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<Integer, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
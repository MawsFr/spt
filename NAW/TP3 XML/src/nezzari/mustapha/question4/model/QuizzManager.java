package nezzari.mustapha.question4.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QuizzManager {

	protected List<Quizz> quizzList;
	protected File quizzFile;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;

	public QuizzManager() {
		this.quizzList = new ArrayList<Quizz>();
		dbf = DocumentBuilderFactory.newInstance(); 
		dbf.setCoalescing(false);
		dbf.setIgnoringElementContentWhitespace(true); 
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
	}

	public void load(String filename) throws SAXException, IOException {
		unload();
		quizzFile = new File(filename);
		Document document = db.parse(quizzFile);

		Element questionnaires = document.getDocumentElement();

		NodeList questionnairesList = questionnaires.getElementsByTagName("questionnaire");

		for(int i = 0; i < questionnairesList.getLength(); i++) {
			Element questionnaire = (Element) questionnairesList.item(i);

			String titre = questionnaire.getFirstChild().getTextContent();
			Quizz quizz = new Quizz(titre);

			Element questions = (Element) questionnaire.getLastChild();
			NodeList questionsNodeList = questions.getChildNodes();
			for(int k = 0; k < questionsNodeList.getLength(); k++) {
				Element question = (Element) questionsNodeList.item(k);
				String enonce = question.getFirstChild().getTextContent();

				Element bonneReponse = (Element) question.getLastChild();
				int bonneReponseIndex = 0;
				int score = Integer.parseInt(bonneReponse.getAttribute("score"));

				List<String> questionsList = new ArrayList<String>();			
				NodeList listReponse = question.getElementsByTagName("reponse");
				for(int j = 0; j < listReponse.getLength(); j++) {
					questionsList.add(listReponse.item(j).getTextContent());
					if(((Element)listReponse.item(j)).getAttribute("id").equals(bonneReponse.getAttribute("reponse"))) {
						bonneReponseIndex = j;
					}
				}

				Question q = new Question(enonce, questionsList, bonneReponseIndex, score);
				quizz.addQuestion(q);
			}
			quizzList.add(quizz);
		}

	}

	public void unload() {
		if(!quizzList.isEmpty()) {
			for(Quizz quizz : quizzList) {
				quizz.unLoad();
			}

			quizzList.clear();
			System.gc();
		}
	}

	public List<Quizz> getQuizzList() {
		return quizzList;
	}
	
	@Override
	public String toString() {
		String s = quizzFile.getName() + "\n";
		for(Quizz q : quizzList) {
			s+= q.toString() + "\n";
		}


		return s;
	}

	public static void main(String[] args) throws SAXException, IOException {
		//Test du quizz manager
		QuizzManager manager = new QuizzManager();
		manager.load("questionnaire.xml");
		System.out.println(manager.quizzList);
	}

}

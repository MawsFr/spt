package nezzari.mustapha.question4.projet.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QuizzManager {

	protected List<Quizz> quizzList;
	protected Quizz currentQuizz;
	protected File quizzFile;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;

	public QuizzManager() {
		this.quizzList = new ArrayList<Quizz>();
		dbf = DocumentBuilderFactory.newInstance(); 
		dbf.setValidating(true);
		dbf.setNamespaceAware(true);
		dbf.setCoalescing(false);
		dbf.setIgnoringElementContentWhitespace(true); 
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
	}


	public void load(File quizzFile) throws SAXException, IOException, XPathExpressionException {
		unload();
		Document document = db.parse(quizzFile);
		this.quizzFile = quizzFile;
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("//questionnaire");
		NodeList questionnairesList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		if(questionnairesList.getLength() <= 0) {
			throw new IllegalArgumentException("Votre fichier ne contient aucun questionnaire ou est invalide !");
		}
		for(int i = 0; i < questionnairesList.getLength(); i++) {
			expr = xpath.compile("titre");
			String titre = (String) expr.evaluate(questionnairesList.item(i), XPathConstants.STRING);
			Quizz quizz = new Quizz(titre);
			expr = xpath.compile("questions/question");
			NodeList questionsNodeList = (NodeList) expr.evaluate(questionnairesList.item(i), XPathConstants.NODESET);
			if(questionsNodeList.getLength() <= 0) {
				throw new IllegalArgumentException("Votre fichier ne contient aucune question ou est invalide !");
			}
			for(int j = 0; j < questionsNodeList.getLength(); j++) {
				expr = xpath.compile("enonce");
				String enonce = (String) expr.evaluate(questionsNodeList.item(j), XPathConstants.STRING);

				expr = xpath.compile("reponse");
				NodeList listReponse = (NodeList) expr.evaluate(questionsNodeList.item(j), XPathConstants.NODESET);
				
				if(listReponse.getLength() <= 0) {
					throw new IllegalArgumentException("Une des questions ne contient aucune reponse ou est invalide !");
				}
				
				Question q = new Question(enonce);

				for(int k = 0; k < listReponse.getLength(); k++) {
					Element reponse = (Element) listReponse.item(k);
					//					String text = (String) xpath.compile(".").evaluate(reponse, XPathConstants.STRING);
					String text = reponse.getTextContent(); 
					int score = Integer.parseInt(reponse.getAttribute("score"));

					Answer answer = new Answer(text, score);

					q.addAnswer(answer);

				}

				quizz.addQuestion(q);
			}
			this.quizzList.add(quizz);
		}
	}
	
	/*
	public void load(String filename) throws SAXException, IOException { //avec questionnaireBak.xml
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
	 */


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

	public static void main(String[] args) throws SAXException, IOException, XPathExpressionException, IllegalArgumentException {
		//Test du quizz manager
		QuizzManager manager = new QuizzManager();
//		manager.load(new File("expBis.xml"));
		manager.load(new File("questionnaire.xml"));
		System.out.println(manager.quizzList);
	}

	public int getNbQuizz() {
		return this.quizzList.size();
	}

	public Quizz getCurrentQuizz() {
		return currentQuizz;
	}

	public void setCurrentQuizz(Quizz quizz) {
		this.currentQuizz = quizz;

	}

}
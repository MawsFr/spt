package nezzari.mustapha.question4.model;

import java.util.ArrayList;
import java.util.List;

/*
 * Représente la balise <questionnaire> (sans s)
 */
public class Quizz {
	
	protected String title;
	protected List<Question> questions;
	
	public Quizz(String title) {
		this.questions = new ArrayList<Question>();
		this.title = title;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	public void unLoad() {
		questions.clear();
		
	}
	
	public Question getQuestion(int i) {
		return this.questions.get(i);
	}
	
	public int getNbQuestions() {
		return this.questions.size();
	}
	
	@Override
	public String toString() {
		String s = title + "\n";
		for(Question q : questions) {
			s += q.toString() + "\n";
		}
		return s;
	}
	
	
	
	
}

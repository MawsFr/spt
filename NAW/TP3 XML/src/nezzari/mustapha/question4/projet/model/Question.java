package nezzari.mustapha.question4.projet.model;

import java.util.ArrayList;
import java.util.List;

public class Question {

	protected String question;
	protected List<Answer> answers;

	public Question(String question) {
		this.question = question;
		this.answers = new ArrayList<Answer>();
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
	
	public String getQuestion() {
		return question;
	}
	
	public Answer getAnswer(int i) {
		return this.answers.get(i);
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public int getNbAnswers() {
		return answers.size();
	}
	
	public boolean isAnswered() {
		for (Answer answer : answers) {
			if(answer.isSelected()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += question + "\n";
		for(int i = 0; i < answers.size(); i++) {
			s+= (i+1) + " : " + answers.get(i) + "\n";
		}
		return s;
	}

	public void reset() {
		for(Answer a : answers) {
			a.reset();
		}
		
	}
	

}

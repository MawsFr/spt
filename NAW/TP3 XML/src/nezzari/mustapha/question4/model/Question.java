package nezzari.mustapha.question4.model;

import java.util.List;

public class Question {

	protected String question;
	protected List<String> answers;
	protected int rightAnswer;
	protected int score;

	public Question(String question, List<String> answers, int rightAnswer, int score) {
		this.question = question;
		this.answers = answers;
		this.rightAnswer = rightAnswer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getAnswer(int i) {
		return this.answers.get(i);
	}
	
	public List<String> getAnswers() {
		return answers;
	}

	public int getNbAnswers() {
		return answers.size();
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
	

}

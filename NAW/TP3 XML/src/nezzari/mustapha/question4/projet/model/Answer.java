package nezzari.mustapha.question4.projet.model;

public class Answer {
	
	protected String text;
	protected int score;
	protected boolean selected;
	
	public Answer(String text, int score) {
		this.text = text;
		this.score = score;
		
	}
	
	public String getText() {
		return text;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isRightAnswer() {
		return score > 0;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	@Override
	public String toString() {
		return this.text;
	}

	public void reset() {
		this.selected = false;
	}
	
}

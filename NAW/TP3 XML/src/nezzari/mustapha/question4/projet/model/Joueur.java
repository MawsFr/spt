package nezzari.mustapha.question4.projet.model;

public class Joueur {

	protected String nickName;
	protected int score;

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public void addScore(int amount) {
		this.score += amount;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return nickName + " : " + score;
	}
	
	
	
	
	
	
}

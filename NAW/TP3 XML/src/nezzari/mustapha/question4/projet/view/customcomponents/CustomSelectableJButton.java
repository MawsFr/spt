package nezzari.mustapha.question4.projet.view.customcomponents;

import java.awt.Color;
import java.awt.Graphics;

import nezzari.mustapha.question4.projet.view.Tools;

public class CustomSelectableJButton extends CustomJButton {

	private static final long serialVersionUID = 7307577214057908291L;
	
	protected boolean isSelected;
	
	public CustomSelectableJButton(String answer) {
		super(answer);

		
	}

	public boolean selected() {
		return this.isSelected;
	}

	public void select(boolean selected) {
		this.isSelected = selected;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(isSelected) {
			colorNormal = colorRolledOver;
		} else {
			colorNormal = Color.decode("#91c842");
		}
		
		super.paintComponent(g);
		
		if(isSelected) {
			g.drawImage(Tools.correct, getWidth() - 2 * Tools.correct.getWidth(), getHeight() - 2 * Tools.correct.getHeight(), Tools.correct.getWidth(), Tools.correct.getHeight(), null);
		}
		
	}
	
	
	
	
	
}

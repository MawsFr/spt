package nezzari.mustapha.question4.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import nezzari.mustapha.question4.view.GameFrame;

public class NextQuestionAction extends AbstractAction {

	
	
	protected GameFrame gameFrame;

	public NextQuestionAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		putValue(NAME, "Question suivante");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.getGamePanel().getQuizzPanel().nextQuizz();
	}
	
	

}

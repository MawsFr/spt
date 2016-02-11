package nezzari.mustapha.question4.projet.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.panels.GamePanel;

public class PlayAction extends AbstractAction {
	private static final long serialVersionUID = 5490022891597502170L;

	protected GameFrame gameFrame;

	public PlayAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		putValue(NAME, "Faire le quizz ->");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.getGamePanel().getQuizzPanel().init();
		gameFrame.getGamePanel().getQuizzPanel().nextQuestion();
		gameFrame.getGamePanel().showView(GamePanel.QUIZZ_PANEL);
	}

}

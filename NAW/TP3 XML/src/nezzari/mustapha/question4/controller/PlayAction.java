package nezzari.mustapha.question4.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import nezzari.mustapha.question4.model.Quizz;
import nezzari.mustapha.question4.view.GameFrame;
import nezzari.mustapha.question4.view.GamePanel;

public class PlayAction extends AbstractAction {
	private static final long serialVersionUID = 5490022891597502170L;

	protected GameFrame gameFrame;
	
	public PlayAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		putValue(NAME, "Faire le quizz ->");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Quizz> quizz = gameFrame.getQuizzManager().getQuizzList();
		if(quizz.size() == 1) {
			gameFrame.getGamePanel().getQuizzPanel().setQuizz(quizz.get(0));
			gameFrame.getGamePanel().showView(GamePanel.QUIZZ_PANEL);
			gameFrame.getGamePanel().getQuizzPanel().nextQuizz();
		} else {
			//go to quizz selector
		}
		
		
	}

}

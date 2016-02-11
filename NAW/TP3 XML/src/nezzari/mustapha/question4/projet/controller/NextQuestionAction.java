package nezzari.mustapha.question4.projet.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import nezzari.mustapha.question4.projet.view.GameFrame;

public class NextQuestionAction extends AbstractAction {
	private static final long serialVersionUID = -6467811797786029531L;
	
	protected GameFrame gameFrame;

	public NextQuestionAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		putValue(NAME, "Question suivante");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!gameFrame.getGamePanel().getQuizzPanel().getCurrentQuestion().isAnswered()) {
			if(JOptionPane.showConfirmDialog(gameFrame, "Attention, vous n'avez pas répondu à toutes les questions, souhaitez-vous vraiment continuer ?", "Vous n'avez pas répondu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE) != JOptionPane.OK_OPTION) {
				return;
			}
			
		}
		
		gameFrame.getGamePanel().getQuizzPanel().nextQuestion();
	}
	
	

}

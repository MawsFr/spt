package nezzari.mustapha.question4.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.xml.sax.SAXException;

import nezzari.mustapha.question4.view.GameFrame;

public class OuvrirQuestionnaireAction extends AbstractAction {
	private static final long serialVersionUID = 5123261592187324928L;

	protected GameFrame gameFrame;

	public OuvrirQuestionnaireAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		putValue(NAME, "Ouvrir un questionnaire (.xml)");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			gameFrame.getQuizzManager().load("questionnaire.xml");
			System.out.println(gameFrame.getQuizzManager().toString());
			gameFrame.getGamePanel().getMenuPanel().getButtonPlay().setVisible(true);
		} catch (SAXException | IOException e1) {
			JOptionPane.showMessageDialog(gameFrame, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		

	}



}

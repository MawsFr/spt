package nezzari.mustapha.question4.projet.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.panels.GamePanel;

public class BackToMenuAction extends AbstractAction {

	private static final long serialVersionUID = 5676017369335191488L;

	protected GameFrame gameFrame;

	public BackToMenuAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		putValue(NAME, "<- Menu");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.getGamePanel().showView(GamePanel.MENU_PANEL);
	}

}

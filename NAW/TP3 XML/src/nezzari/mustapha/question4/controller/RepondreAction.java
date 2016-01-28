package nezzari.mustapha.question4.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import nezzari.mustapha.question4.view.CustomJButton;
import nezzari.mustapha.question4.view.GameFrame;

public class RepondreAction extends AbstractAction {
	private static final long serialVersionUID = 2653772073855643078L;

	protected GameFrame gameFrame;

	public RepondreAction(GameFrame gameFrame, String name) {
		this.gameFrame = gameFrame;
		putValue(NAME, name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameFrame.getGamePanel().getQuizzPanel().reply(((CustomJButton)e.getSource()).getId());
		
	}

}

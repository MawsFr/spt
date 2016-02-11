package nezzari.mustapha.question4.projet.view.panels;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import nezzari.mustapha.question4.projet.controller.OuvrirQuestionnaireAction;
import nezzari.mustapha.question4.projet.controller.PlayAction;
import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.Tools;
import nezzari.mustapha.question4.projet.view.customcomponents.CustomJButton;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = -373881337109291940L;

	protected GameFrame gameFrame;
	protected CustomJButton buttonOpen;
	protected CustomJButton buttonPlay;
	protected boolean shouldReload;

	public MenuPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setLayout(null);

		buttonOpen = new CustomJButton(new OuvrirQuestionnaireAction(gameFrame));
		Insets insets = gameFrame.getGamePanel().getInsets();
		buttonOpen.setBounds(insets.left + GameFrame.WIDTH/2 - 250 / 2, insets.top + GameFrame.HEIGHT / 2 - 100 / 2, 250, 100);

		buttonPlay = new CustomJButton(Tools.buttonIcon, new PlayAction(gameFrame));
		buttonPlay.setBounds(GameFrame.WIDTH - 190 - 190 / 4, GameFrame.HEIGHT - 92 - 92/ 2, 190, 92);
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());
		init();
		this.add(buttonOpen);
		this.add(buttonPlay);

	}

	public void init() {
		buttonPlay.setVisible(false);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Tools.drawPanelBackground(g, this);

	}
	
	public CustomJButton getButtonPlay() {
		return buttonPlay;
	}

}

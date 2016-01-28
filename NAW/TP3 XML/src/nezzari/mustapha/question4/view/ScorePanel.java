package nezzari.mustapha.question4.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 8415741211709630826L;

	public ScorePanel(GameFrame gameFrame) {
		setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
		setSize(getPreferredSize());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Tools.drawPanelBackground(g, this);
		g.setColor(Color.BLACK);
		g.drawString("Score : ", 100, 100);
	}

}

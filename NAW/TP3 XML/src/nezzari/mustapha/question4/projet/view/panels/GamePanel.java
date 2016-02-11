package nezzari.mustapha.question4.projet.view.panels;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import nezzari.mustapha.question4.projet.view.GameFrame;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 5363642303672521000L;
	
	protected GameFrame gameFrame;
	
	public static final String MENU_PANEL = "MENUPANEL";
	public static final String SELECTION_PANEL = "SELECTIONPANEL";
	public static final String QUIZZ_PANEL = "QUIZZPANEL";
	public static final String SCORE_PANEL = "SCOREPANEL";
	
	
	protected MenuPanel menuPanel;
	protected SelectionPanel selectionPanel;
	protected QuizzPanel quizzPanel;
	protected ScorePanel scorePanel;
	
	public GamePanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
		setSize(getPreferredSize());
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new CardLayout());
		
	}
	
	public void showView(String panel) {
		((CardLayout) this.getLayout()).show(this, panel);
	}

	public void init() {
		menuPanel = new MenuPanel(gameFrame);
		selectionPanel = new SelectionPanel(gameFrame);
		quizzPanel = new QuizzPanel(gameFrame);
		scorePanel = new ScorePanel(gameFrame);
		
		this.add(MENU_PANEL, menuPanel);
		this.add(SELECTION_PANEL, selectionPanel);
		this.add(QUIZZ_PANEL, quizzPanel);
		this.add(SCORE_PANEL, scorePanel);
	}
	
	public MenuPanel getMenuPanel() {
		return menuPanel;
	}
	
	public QuizzPanel getQuizzPanel() {
		return quizzPanel;
	}
	
	public ScorePanel getScorePanel() {
		return scorePanel;
	}
	
	public SelectionPanel getSelectionPanel() {
		return selectionPanel;
	}
}

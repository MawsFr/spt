package nezzari.mustapha.question4.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import nezzari.mustapha.question4.model.QuizzManager;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = -587496443123220607L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	protected GamePanel gamePanel;
	protected QuizzManager quizzManager;
	
	public GameFrame() {
		this.quizzManager = new QuizzManager();
		gamePanel = new GamePanel(this);
		gamePanel.init();
		this.setContentPane(gamePanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new GameFrame();
				
			}
		});
	}

	public QuizzManager getQuizzManager() {
		return quizzManager;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
}

package nezzari.mustapha.question4.projet.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import nezzari.mustapha.question4.projet.model.Joueur;
import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.model.QuizzManager;
import nezzari.mustapha.question4.projet.view.panels.GamePanel;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = -587496443123220607L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	protected GamePanel gamePanel;
	protected QuizzManager quizzManager;
	protected Joueur joueur;

	public GameFrame() {
		this.quizzManager = new QuizzManager();
		joueur = new Joueur();
		gamePanel = new GamePanel(this);
		gamePanel.init();
		this.setContentPane(gamePanel);

		Timer timer = new Timer(Tools.ANIMATION_SPEED, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});


		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quizzManager.unload();
			};
		});

		setIconImage(new ImageIcon("images/icone.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		if(Tools.animated)
			timer.start();
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

	public Joueur getJoueur() {
		return joueur;
	}

	public Quizz getCurrentQuizz() {
		return this.gamePanel.getQuizzPanel().getCurrentQuizz();
	}

	public boolean hasMoreQuizz() {
		return quizzManager.getQuizzList().indexOf(gamePanel.getQuizzPanel().getCurrentQuizz()) < quizzManager.getNbQuizz() - 1;
	}

	public void nextQuizz() {
		gamePanel.getQuizzPanel().setQuizz(getQuizzManager().getQuizzList().get(quizzManager.getQuizzList().indexOf(gamePanel.getQuizzPanel().getCurrentQuizz()) + 1));
	}
}

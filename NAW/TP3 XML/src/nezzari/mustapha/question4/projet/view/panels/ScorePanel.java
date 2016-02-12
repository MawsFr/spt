package nezzari.mustapha.question4.projet.view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import nezzari.mustapha.question4.projet.model.Answer;
import nezzari.mustapha.question4.projet.model.Joueur;
import nezzari.mustapha.question4.projet.model.Question;
import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.Tools;
import nezzari.mustapha.question4.projet.view.customcomponents.CustomJButton;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = 8415741211709630826L;

	protected GameFrame gameFrame;
	protected List<Integer> answerList;
	protected List<JPanel> questionPanels;
	protected JPanel centerPanel;
	protected JLabel scoreLabel;
	protected JLabel phraseLabel;
	protected int currentScore;
	protected CustomJButton backMenuButton, nextQuizzButton;

	protected Quizz currentQuizz;


	public ScorePanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		questionPanels = new ArrayList<JPanel>();
		scoreLabel = new JLabel();
		phraseLabel = new JLabel();
		this.centerPanel = new JPanel();
		centerPanel.setAlignmentX(LEFT_ALIGNMENT);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		AbstractAction backMenuAction = new AbstractAction() {

			private static final long serialVersionUID = 4588460829650179444L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameFrame.getQuizzManager().getQuizzList().size() > 1) {
					gameFrame.getGamePanel().showView(GamePanel.SELECTION_PANEL);
				} else {
					gameFrame.getGamePanel().showView(GamePanel.MENU_PANEL);
				}
			}
		};

		this.backMenuButton = new CustomJButton(Tools.voirQuizz, backMenuAction);

		//TODO : Ajouter les bouton

		AbstractAction nextQuizzAction = new AbstractAction() {
			private static final long serialVersionUID = -2165362435442786904L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameFrame.hasMoreQuizz()) {
					gameFrame.nextQuizz();
					gameFrame.getGamePanel().getQuizzPanel().init();
					gameFrame.getGamePanel().getQuizzPanel().nextQuestion();
					gameFrame.getGamePanel().showView(GamePanel.QUIZZ_PANEL);
				} else {
					JOptionPane.showMessageDialog(ScorePanel.this, "Il n'ya plus d'autre questionnaire, vous allez être redirigé vers le menu principal", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					gameFrame.getGamePanel().showView(GamePanel.MENU_PANEL);	
				}


			}
		};

		this.nextQuizzButton = new CustomJButton(Tools.quizzSuivant, nextQuizzAction);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setOpaque(false);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder());
		buttonPanel.add(backMenuButton);
		buttonPanel.add(nextQuizzButton);
		centerPanel.add(buttonPanel);

		centerPanel.add(phraseLabel);
		centerPanel.add(scoreLabel);
		setLayout(null);


		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());


		setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
		setSize(getPreferredSize());
		JScrollPane scroll = new JScrollPane(centerPanel);
		centerPanel.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		centerPanel.setBorder(BorderFactory.createEmptyBorder());
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setBounds(10, 60, GameFrame.WIDTH - 10, GameFrame.HEIGHT - 60);
		this.add(scroll);


	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Tools.drawPanelBackground(g, this);
		//		g.setColor(Color.BLACK);
		//		g.drawString("Score : ", 100, 100);
	}

	public void calculateScore() {
		currentScore = 0;
		currentQuizz = gameFrame.getGamePanel().getQuizzPanel().getCurrentQuizz();
		Joueur joueur = gameFrame.getJoueur();

		for(JPanel jp : questionPanels) {
			centerPanel.remove(jp);
		}
		questionPanels.clear();

		if(gameFrame.getQuizzManager().getNbQuizz() > 1) {
			nextQuizzButton.setVisible(true);
		} else {
			nextQuizzButton.setVisible(false);
		}

		phraseLabel.setText("<html><p style=\"font-size:16px\">Vous avez terminé le question <strong>" + currentQuizz.getTitle() + "</strong></p>");


		List<Question> questions = currentQuizz.getQuestions();
		for(Question question : questions) {
			JPanel questionPanel = new JPanel();
			questionPanel.setBorder(BorderFactory.createEmptyBorder());
			questionPanel.setOpaque(false);
			questionPanel.setSize(200, 300);
			questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
			questionPanel.setAlignmentX(LEFT_ALIGNMENT);
			JLabel enonce = new JLabel(question.getQuestion());
			enonce.setSize(enonce.getWidth(), 100);
			enonce.setFont(enonce.getFont().deriveFont(Font.BOLD));
			enonce.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
			questionPanel.add(enonce);
			for(Answer answer : question.getAnswers()) {
				JPanel answerPanel = new JPanel();
				answerPanel.setBorder(BorderFactory.createEmptyBorder());
				answerPanel.setOpaque(false);
				answerPanel.setAlignmentX(LEFT_ALIGNMENT);
				answerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
				JLabel iconLabel = new JLabel();
				JLabel answerLabel = new JLabel();
				if(answer.isSelected()) {
					joueur.addScore(answer.getScore());
					currentScore += answer.getScore();
					answerLabel.setText("<html><p style=\"margin-left: 3px;\"><strong>" + answer.getText() + "</strong></p>");
					if(answer.isRightAnswer()) {
						iconLabel.setIcon(new ImageIcon(Tools.correct));
						answerLabel.setForeground(Tools.GREEN);
					} else {
						iconLabel.setIcon(new ImageIcon(Tools.uncorrect));
					}
				} else {
					if(answer.isRightAnswer()) {
						iconLabel.setIcon(new ImageIcon(Tools.correct));
						answerLabel.setForeground(Tools.GREEN);
					} else {
						iconLabel.setIcon(new ImageIcon(Tools.empty));
					}
					answerLabel.setText("\t" + answer.getText());

				}
				answerPanel.add(iconLabel);
				answerPanel.add(answerLabel);



				questionPanel.add(answerPanel);
			}

			if(!question.isAnswered()) {
				JLabel messageLabel = new JLabel("Vous n'avez pas répondu à cette question");
				messageLabel.setAlignmentX(LEFT_ALIGNMENT);
				questionPanel.add(messageLabel);
			}

			scoreLabel.setText("Votre score est de " + currentQuizz.getNbRightAnswers() + " sur " + currentQuizz.getNbQuestions() + " soit " + currentScore + " points (Vous avez au total " + joueur.getScore() + " point(s) !)");
			centerPanel.add(questionPanel);
			questionPanels.add(questionPanel);

		}
	}

}

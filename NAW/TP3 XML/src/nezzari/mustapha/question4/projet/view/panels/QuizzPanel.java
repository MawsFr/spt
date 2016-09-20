package nezzari.mustapha.question4.projet.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import nezzari.mustapha.question4.projet.controller.NextQuestionAction;
import nezzari.mustapha.question4.projet.model.Question;
import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.Tools;
import nezzari.mustapha.question4.projet.view.customcomponents.CustomJButton;
import nezzari.mustapha.question4.projet.view.customcomponents.CustomSelectableJButton;

public class QuizzPanel extends JPanel {
	private static final long serialVersionUID = -3884125178539913282L;
	
	
	protected int currentQuestion;
	protected JTextArea questionArea;

	protected CustomJButton buttonNextQuestion;
	protected List<CustomSelectableJButton> buttonAnswer;
	
	protected GameFrame gameFrame;

	public QuizzPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setLayout(null);
		setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
		setSize(getPreferredSize());
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());
		
		buttonNextQuestion = new CustomJButton(new NextQuestionAction(gameFrame));
		buttonNextQuestion.setBounds(GameFrame.WIDTH - 190 - 190 / 4, GameFrame.HEIGHT - 92 - 92/ 2, 190, 92);
		
		questionArea = new JTextArea();
		questionArea.setBounds(60, 110 , GameFrame.WIDTH - 120, 80);
		questionArea.setEditable(false);
		questionArea.setForeground(Tools.GREEN);
		questionArea.setLineWrap(true);
		questionArea.setOpaque(false);
		questionArea.setBorder(BorderFactory.createEmptyBorder());
		questionArea.setFont(new Font("PT Serif", Font.PLAIN, 20));
		
		JScrollPane scroll = new JScrollPane (questionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(60, 110 , GameFrame.WIDTH - 120, 80);
		scroll.setOpaque(false);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getViewport().setBackground(Color.WHITE);
		
		buttonAnswer = new ArrayList<CustomSelectableJButton>();
		this.add(buttonNextQuestion);
		this.add(scroll);
		
		Tools.addBackToMenuButton(gameFrame, this);
	}
	
	public void init() {
		getCurrentQuizz().reset();
		buttonNextQuestion.setText("Question suivante");
		this.currentQuestion = -1;
	}
	
	public void setQuizz(Quizz quizz) {
		this.gameFrame.getQuizzManager().setCurrentQuizz(quizz);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Tools.drawPanelBackground(g, this);
		g.setColor(Tools.GREEN);
		g.drawRoundRect(50, 95 , GameFrame.WIDTH - 100, 100, 5, 5);
		g.setColor(Color.WHITE);
		g.drawLine(90, 95, 230, 95);
//		Font f = g.getFont();
		//Dessin de la question
		g.setFont(new Font("PT Serif", Font.BOLD, 16));
		g.setColor(Tools.GREEN);
		g.drawString("Question N° " + (currentQuestion + 1), 100, 100);
		
		
	}

	public void nextQuestion() {
		if(currentQuestion == getCurrentQuizz().getNbQuestions() - 2) {
			buttonNextQuestion.setText("Finir le questionnaire");
			++currentQuestion;
		} else if(currentQuestion >= getCurrentQuizz().getNbQuestions() - 1) {
			gameFrame.getGamePanel().showView(GamePanel.SCORE_PANEL);
			gameFrame.getGamePanel().getScorePanel().calculateScore();
			return;
		} else {
			++currentQuestion;
		}
		
		questionArea.setText(getCurrentQuizz().getQuestion(currentQuestion).getQuestion());
		questionArea.setCaretPosition(0);
		
		for(CustomJButton cjb : buttonAnswer) {
			this.remove(cjb);
		}
		
		buttonAnswer.clear();
		final Question cq = getCurrentQuizz().getQuestion(currentQuestion);
		for(int i = 0; i < cq.getNbAnswers(); i++) {
			final CustomSelectableJButton selectableButton = new CustomSelectableJButton(cq.getAnswer(i).toString());
			selectableButton.setBounds((i * 150) + 30, 215, 140, 100);
			selectableButton.setId(i);
			this.add(selectableButton);
			
			selectableButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					selectableButton.select(!selectableButton.selected());
					cq.getAnswer(selectableButton.getId()).setSelected(selectableButton.selected());
					for(CustomSelectableJButton csb : buttonAnswer) {
						if(csb != selectableButton) {
							csb.select(false);
							cq.getAnswer(csb.getId()).setSelected(false);
							csb.repaint();
						}
					}
				}
			});
			
			buttonAnswer.add(selectableButton);
		}
		
		repaint();
		
	}
	
	public Quizz getCurrentQuizz() {
		return gameFrame.getQuizzManager().getCurrentQuizz();
	}

	public Question getCurrentQuestion() {
		return this.getCurrentQuizz().getQuestion(currentQuestion);
	}
	
}

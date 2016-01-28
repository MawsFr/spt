package nezzari.mustapha.question4.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import nezzari.mustapha.question4.controller.NextQuestionAction;
import nezzari.mustapha.question4.controller.RepondreAction;
import nezzari.mustapha.question4.model.Question;
import nezzari.mustapha.question4.model.Quizz;

public class QuizzPanel extends JPanel {
	private static final long serialVersionUID = -3884125178539913282L;
	
	protected Quizz quizz;
	protected int currentQuestion;
	protected JTextArea questionArea;

	protected CustomJButton buttonNextQuestion;
	protected List<CustomJButton> buttonReponse;

	protected GameFrame gameFrame;

	public QuizzPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		this.setLayout(null);
		setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
		setSize(getPreferredSize());
		
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
		
		buttonReponse = new ArrayList<CustomJButton>();
		init();
		this.add(buttonNextQuestion);
		this.add(scroll);
	}
	
	public void init() {
		buttonReponse.clear();
		this.currentQuestion = -1;
	}
	
	public void setQuizz(Quizz quizz) {
		this.quizz = quizz;
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

	public void nextQuizz() {
		if(currentQuestion == quizz.getNbQuestions() - 2) {
			buttonNextQuestion.setText("Finir le questionnaire");
			++currentQuestion;
		} else if(currentQuestion >= quizz.getNbQuestions() - 1) {
			gameFrame.getGamePanel().showView(GamePanel.SCORE_PANEL);
		} else {
			++currentQuestion;
		}
		
		questionArea.setText(quizz.getQuestion(currentQuestion).getQuestion());
		questionArea.setCaretPosition(0);
		
		Question cq = quizz.getQuestion(currentQuestion);
		for(int i = 0; i < cq.getNbAnswers(); i++) {
			CustomJButton button = new CustomJButton(new RepondreAction(gameFrame));
			button.setId(i);
			buttonReponse.add(button);
		}
		
		repaint();
		
	}

	
	public void reply(int i) {
		
	}

}

package nezzari.mustapha.question4.projet.view.panels;

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nezzari.mustapha.question4.projet.controller.PlayAction;
import nezzari.mustapha.question4.projet.controller.QuizzListModel;
import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.Tools;
import nezzari.mustapha.question4.projet.view.customcomponents.CustomJButton;
import nezzari.mustapha.question4.projet.view.customcomponents.QuizzCellRenderer;

public class SelectionPanel extends JPanel {
	private static final long serialVersionUID = 8868009712063334645L;

	protected GameFrame gameFrame;
//	protected JPanel quizzSelectionPanel;
	protected JList<Quizz> quizzSelectionList;
	protected CustomJButton buttonPlay;
	


	public SelectionPanel(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
//		quizzSelectionPanel = new JPanel();
//		quizzSelectionPanel.setLayout(new BoxLayout(quizzSelectionPanel, BoxLayout.PAGE_AXIS));
//		quizzSelectionPanel.setBounds(10, 60, GameFrame.WIDTH - 10, GameFrame.HEIGHT - 60);
//		
//		quizzSelectionPanel.setOpaque(false);
//		quizzSelectionPanel.setBorder(BorderFactory.createEmptyBorder());
	    setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());
		
		buttonPlay = new CustomJButton(Tools.buttonIcon, new PlayAction(gameFrame));
		buttonPlay.setBounds(GameFrame.WIDTH - 190 - 190 / 4, GameFrame.HEIGHT - 92 - 92/ 2, 190, 92);
		buttonPlay.setVisible(false);
		
		quizzSelectionList = new JList<Quizz>();
		quizzSelectionList.setCellRenderer(new QuizzCellRenderer());
		quizzSelectionList.setOpaque(false);
		quizzSelectionList.setBorder(BorderFactory.createEmptyBorder());
		
		
		quizzSelectionList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
			        if (quizzSelectionList.getSelectedIndex() == -1) {
			            buttonPlay.setVisible(false);

			        } else {
			        	gameFrame.getGamePanel().getQuizzPanel().setQuizz(quizzSelectionList.getSelectedValue());
			            buttonPlay.setVisible(true);
			        }
			    }
			}
		});


		JScrollPane scroll = new JScrollPane(quizzSelectionList);
		
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(null);
		scroll.setBounds(10, 60, GameFrame.WIDTH - 300, GameFrame.HEIGHT - 150);
		this.add(scroll);
		this.add(buttonPlay);
		
		Tools.addBackToMenuButton(gameFrame, this);
	}

	public void init() {
		quizzSelectionList.setModel(new QuizzListModel(gameFrame.getQuizzManager()));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Tools.drawPanelBackground(g, this);

	}



}

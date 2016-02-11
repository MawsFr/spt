package nezzari.mustapha.question4.projet.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.view.GameFrame;
import nezzari.mustapha.question4.projet.view.panels.GamePanel;

public class OuvrirQuestionnaireAction extends AbstractAction {
	private static final long serialVersionUID = 5123261592187324928L;

	protected GameFrame gameFrame;
	protected JFileChooser fileChooser;

	public OuvrirQuestionnaireAction(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		fileChooser = new JFileChooser(".");
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
			     "xml files (*.xml)", "xml");

		fileChooser.setDialogTitle("Ouvrez un fichier contenant des quizz");
			// set selected filter
		fileChooser.setFileFilter(xmlfilter);

		putValue(NAME, "Ouvrir un questionnaire (.xml)");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			if(fileChooser.showOpenDialog(gameFrame) == JFileChooser.OPEN_DIALOG) {
				File file= fileChooser.getSelectedFile();
				gameFrame.getQuizzManager().load(file);
				System.out.println(gameFrame.getQuizzManager().toString());
				//TODO : Faire des verification sur le fichier
				
				List<Quizz> quizz = gameFrame.getQuizzManager().getQuizzList();
				if(quizz.size() == 1) {
					gameFrame.getGamePanel().getMenuPanel().getButtonPlay().setVisible(true);
					gameFrame.getGamePanel().getQuizzPanel().setQuizz(gameFrame.getQuizzManager().getQuizzList().get(0));
					
				} else {
//					//go to quizz selector
	                gameFrame.getGamePanel().getSelectionPanel().init();
					gameFrame.getGamePanel().showView(GamePanel.SELECTION_PANEL);
					
				}
			}
			
			
		} catch (SAXException | IOException | XPathExpressionException | IllegalArgumentException e1) {
			JOptionPane.showMessageDialog(gameFrame, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		

	}



}

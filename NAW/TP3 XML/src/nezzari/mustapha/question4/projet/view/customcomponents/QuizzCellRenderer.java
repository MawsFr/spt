package nezzari.mustapha.question4.projet.view.customcomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import nezzari.mustapha.question4.projet.model.Quizz;
import nezzari.mustapha.question4.projet.view.Tools;

public class QuizzCellRenderer extends JLabel implements ListCellRenderer<Quizz>{
	private static final long serialVersionUID = -8825856580162713990L;
	
	protected boolean isSelected;
	
	public QuizzCellRenderer() {
		setIcon(Tools.icone);
		setOpaque(false);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Quizz> list, Quizz quizz, int index,
			boolean isSelected, boolean cellHasFocus) {
		setText(quizz.getTitle());

        Color background = null;
        Color foreground = null;
		
        this.isSelected = isSelected;
		
		setBackground(background);
        setForeground(foreground);
		return this;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(isSelected) {
			g.drawImage(Tools.correct, getWidth() - Tools.correct.getWidth(), getHeight() - Tools.correct.getHeight() , null);
		}
		
	}

}

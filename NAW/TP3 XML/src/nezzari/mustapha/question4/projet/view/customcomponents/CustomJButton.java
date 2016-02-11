package nezzari.mustapha.question4.projet.view.customcomponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CustomJButton extends JButton {
	private static final long serialVersionUID = -4746804009575068647L;
	
	protected Color colorNormal = Color.decode("#91c842");
	protected Color colorRolledOver = Color.decode("#91e842");
	protected Color colorDown = Color.decode("#91b842");

	protected int id;
	
	protected String name;
	protected BufferedImage image;
	protected BufferedImage imageRolledOver;

	public CustomJButton(String text) {
		super(text);
		this.name = text;
		setRolloverEnabled(true);
		setBorder(BorderFactory.createEmptyBorder());
	}

	public CustomJButton(BufferedImage image, AbstractAction action) {
		this(action);
		
		this.image = image;
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		setSize(getPreferredSize());
		imageRolledOver = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		imageRolledOver.createGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		RescaleOp rescaleOp = new RescaleOp(1.2f, 15, null);
		rescaleOp.filter(imageRolledOver, imageRolledOver);  // Source and destination are the same.
	}

	public CustomJButton(AbstractAction action) {
		super(action);
		name = (String) action.getValue(Action.NAME);
		setRolloverEnabled(true);
		setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image == null){
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(colorNormal.darker());
			g.fillRect(5, 5, getWidth(), getHeight());
			FontMetrics fm = g.getFontMetrics();
			int nameWidth = getWidth() / 2 - fm.stringWidth(name) /2;
			if(getModel().isPressed()) {
				g.setColor(colorDown);
				g.fillRect(3, 3, getWidth() - 5, getHeight() - 5);
				g.setColor(Color.WHITE);
				g.drawString(this.name , nameWidth, (this.getHeight() / 2) + 3);
			} else if(getModel().isRollover()) {
				g.setColor(colorRolledOver);
				g.fillRect(0, 0, getWidth() - 5, getHeight() - 5);
				g.setColor(Color.WHITE);
				g.drawString(this.name , nameWidth, (this.getHeight() / 2));
			} else {
				g.setColor(colorNormal);
				g.fillRect(0, 0, getWidth() - 5, getHeight() - 5);
				g.setColor(Color.WHITE);
				g.drawString(this.name , nameWidth, (this.getHeight() / 2));

			}
		} else {
			if(getModel().isPressed()){
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			} else if(getModel().isRollover()) {
				g.drawImage(imageRolledOver, 0, 0, getWidth(), getHeight(), null);
				
			} else {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			}
			
			
		}
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		this.name = getText();
	}

}
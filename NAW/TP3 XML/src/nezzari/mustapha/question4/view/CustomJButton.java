package nezzari.mustapha.question4.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CustomJButton extends JButton {
	private static final long serialVersionUID = -4746804009575068647L;

	protected int id;
	
	protected String name;
	protected BufferedImage image;
	protected BufferedImage imageRolledOver;

	protected CustomJButton(BufferedImage image, AbstractAction action) {
		this(action);
		
		this.image = image;
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
			g.setColor(Color.decode("#91c842").darker());
			g.fillRect(5, 5, getWidth(), getHeight());
			if(getModel().isPressed()) {
				g.setColor(Color.decode("#91b842"));
				g.fillRect(3, 3, getWidth() - 5, getHeight() - 5);
				g.setColor(Color.WHITE);
				g.drawString(this.name , this.getWidth() / 16, (this.getHeight() / 2) + 3);
			} else if(getModel().isRollover()) {
				g.setColor(Color.decode("#91e842"));
				g.fillRect(0, 0, getWidth() - 5, getHeight() - 5);
				g.setColor(Color.WHITE);
				g.drawString(this.name , this.getWidth() / 16, (this.getHeight() / 2));
			} else {
				g.setColor(Color.decode("#91c842"));
				g.fillRect(0, 0, getWidth() - 5, getHeight() - 5);
				g.setColor(Color.WHITE);
				g.drawString(this.name , this.getWidth() / 16, (this.getHeight() / 2));

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
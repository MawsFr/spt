package nezzari.mustapha.question4.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tools {

	protected static BufferedImage logo;
	protected static Font f2 = new Font("PT Serif", Font.BOLD, 30);
	public static final Color GREEN = Color.decode("#91c842");
	
	static {
		try {
			logo = ImageIO.read(new File("images/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void drawPanelBackground(Graphics g, Component c) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());

		g.setColor(GREEN);
		g.fillRect(0, 0, c.getWidth(), 105 / 2);
		
		
//		FontMetrics fm = g.getFontMetrics();
		Font f = g.getFont();
		
		g.setFont(f2);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Quizz de culture générale", 203, 37);
		g.setColor(Color.WHITE);
		g.drawString("Quizz de culture générale", 200, 35);
		
		g.drawImage(logo, 0, 0, logo.getWidth() / 2, logo.getHeight() / 2, null);
		
		g.setFont(f);
	}
	
}

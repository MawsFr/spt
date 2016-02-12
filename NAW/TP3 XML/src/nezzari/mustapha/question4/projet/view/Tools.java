package nezzari.mustapha.question4.projet.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import nezzari.mustapha.question4.projet.controller.BackToMenuAction;
import nezzari.mustapha.question4.projet.view.customcomponents.CustomJButton;

public class Tools {
	public static boolean animated = true; //CHANGEZ ICI POUR ACTIVER OU DESACTIVER LES ANIMATIONS

	public static BufferedImage logo;
	public static BufferedImage correct;
	public static BufferedImage uncorrect;
	public static BufferedImage empty;
	public static BufferedImage quizzSuivant;
	public static BufferedImage voirQuizz;
	public static BufferedImage buttonIcon;
	public static ImageIcon icone;
	
	public static int ANIMATION_SPEED = 1;


	protected static Font f2 = new Font("PT Serif", Font.BOLD, 30);
	public static final Color GREEN = Color.decode("#91c842");

	protected static Timer timer;

	protected static int xVert, yVert;
	protected static int xHor, yHor;

	protected static int tileSize = 15;
	public static Random r = new Random();

	protected static int delay;


	static {
		try {

			yVert = 0;
			xHor = 0;
			yHor = r.nextInt(GameFrame.WIDTH);
			xVert = r.nextInt(GameFrame.HEIGHT);
			logo = ImageIO.read(new File("images/logo.png"));
			correct = ImageIO.read(new File("images/correct.png"));
			uncorrect = ImageIO.read(new File("images/wrong.png"));
			empty = ImageIO.read(new File("images/vide.png"));
			quizzSuivant = ImageIO.read(new File("images/quizz-suivant.png"));
			voirQuizz = ImageIO.read(new File("images/voirQuizz.png"));
			icone = new ImageIcon("images/icone.png");
			buttonIcon = ImageIO.read(new File("images/boutonPlay.png"));
			if(System.getProperty("os.name").equalsIgnoreCase("Linux")) {
				ANIMATION_SPEED = 3;
			}
			
			timer = new Timer(ANIMATION_SPEED, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					++xHor;
					++yVert;

					if(xHor> GameFrame.WIDTH)  {
						xHor = -tileSize;
						yHor = r.nextInt(GameFrame.WIDTH);
					}

					if(yVert > GameFrame.HEIGHT)  {
						yVert = -tileSize;
						xVert = r.nextInt(GameFrame.HEIGHT);
					}
				}
			});
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					if(animated)
						timer.start();

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void drawPanelBackground(Graphics g, Component c) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());

		g.setColor(GREEN);

		if(animated){
			g.fillRect(xHor, yHor, tileSize, tileSize);
			g.fillRect(xVert, yVert, tileSize, tileSize);
		}

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

	public static CustomJButton addBackToMenuButton(GameFrame gameFrame, JComponent toDraw) {
		CustomJButton button = new CustomJButton(new BackToMenuAction(gameFrame));
		button.setBounds(10, GameFrame.HEIGHT - 60, 100, 50);
		toDraw.add(button);
		return button;
	}

}

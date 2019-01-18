package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import library.IConstants;

@SuppressWarnings("serial")
public class UiCoccyx extends UiFrame implements IConstants{
	
	private BufferedImage image;
	private Graphics graphics;

	public UiCoccyx(BufferedImage pImage) {
		super(WINDOW_TEXT, WINDOW_X_POSITION, WINDOW_Y_POSITION, pImage.getWidth(), pImage.getHeight());
		image = pImage;
		graphics = image.getGraphics();
		graphics.setFont(new Font(null, Font.PLAIN, 50));
	}
	
	public void setImage(BufferedImage pImage) {
		image = pImage;
		repaint();
	}
	public BufferedImage getImage() {
		return image;
	}
	public void changeColor(Color pColor) {
		graphics.setColor(pColor);
	}
	
	public void drawText(String pText, int x, int y) {
		graphics.drawString(pText, x, y);
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

}

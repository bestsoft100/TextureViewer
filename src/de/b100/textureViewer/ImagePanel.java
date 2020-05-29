package de.b100.textureViewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private static final BufferedImage missingIcon = Utils.loadImage("missing.png");
	
	private ImageFile imageFile;
	
	private int zoom = 1;
	private boolean repeat = false;
	
	public ImagePanel() {
		
	}
	
	public ImagePanel(ImageFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(imageFile != null && imageFile.isValidImage()) {
			if(!repeat) {
				g.drawImage(getImage(), (getWidth() - getImage().getWidth() * zoom) / 2, (getHeight() - getImage().getHeight() * zoom) / 2, getImage().getWidth() * zoom, getImage().getHeight() * zoom, null);
			}else {
				int w = getImage().getWidth() * getZoom();
				int h = getImage().getHeight() * getZoom();
				
				int cx = getWidth() / w + 1;
				int cy = getHeight() / h + 1;
				
				for(int i=0; i < cx; i++) {
					for(int j=0; j < cy; j++) {
						g.drawImage(getImage(), i*w, j*h, w, h, null);
					}
				}
			}
		}else {
			g.setColor(Color.lightGray);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(missingIcon, (getWidth() - missingIcon.getWidth())/2, (getHeight() - missingIcon.getHeight()) / 2, null);
		}
	}
	
	public ImageFile getImageFile() {
		return imageFile;
	}
	
	public BufferedImage getImage() {
		return getImageFile().getImage();
	}
	
	public void setImageFile(ImageFile imageFile) {
		this.imageFile = imageFile;
		repaint();
	}
	
	public void setZoom(int zoom) {
		if(zoom < 1)zoom = 1;
		if(zoom > 32)zoom = 32;
		this.zoom = zoom;
		repaint();
	}
	
	public int getZoom() {
		return zoom;
	}
	
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
		repaint();
	}
	
	public boolean isRepeat() {
		return repeat;
	}
	
	public void setBackground(Color bg) {
		System.out.println("Change Background "+bg.getRed()+" "+bg.getGreen()+" "+bg.getBlue());
		super.setBackground(bg);
		repaint();
	}
	
	
}

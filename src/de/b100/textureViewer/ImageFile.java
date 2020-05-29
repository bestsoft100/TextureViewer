package de.b100.textureViewer;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageFile {
	
	private BufferedImage image;
	
	private File file;
	
	public ImageFile(String path) {
		this(new File(path));
	}
	
	public ImageFile(File file) {
		this.file = file;
		
		reload();
	}
	
	public void reload() {
		try {
			image = ImageIO.read(file);
		}catch (Exception e) {
			image = null;
		}
	}
	
	public boolean isValidImage() {
		return image != null && image.getWidth() > 0 && image.getHeight() > 0;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public File getFile() {
		return file;
	}
	
}

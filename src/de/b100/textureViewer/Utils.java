package de.b100.textureViewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Utils {
	
	private static BufferedImage missingTexture;
	
	static {
		missingTexture = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
		Graphics g = missingTexture.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 2, 2);
		g.setColor(Color.magenta);
		g.fillRect(0, 0, 1, 1);
		g.fillRect(1, 1, 1, 1);
	}
	
	public static BufferedImage loadImage(String path) {
		return loadImage(new File(path));
	}
	
	public static BufferedImage loadImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (Exception e) {
			return missingTexture;
		}
	}
	
	public static String[] toArray(ArrayList<String> list) {
		String[] arr = new String[list.size()];
		int i=0;
		while(i < list.size()){
			arr[i] = list.get(i);
			i++;
		}
		return arr;
	}
	
}

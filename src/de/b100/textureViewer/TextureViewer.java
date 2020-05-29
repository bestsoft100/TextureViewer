package de.b100.textureViewer;

import javax.swing.UIManager;

public class TextureViewer {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new TextureViewer();
	}
	
	public TextureViewer() {
		new MainWindow();
	}
	
}

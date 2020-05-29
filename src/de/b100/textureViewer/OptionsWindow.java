package de.b100.textureViewer;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.b100.swing.JColorButton;
import de.b100.swing.JGridPanel;
import de.b100.swing.Window;

public class OptionsWindow extends Window{

	private static final long serialVersionUID = 1L;
	
	private MainWindow mainWindow;
	
	private JGridPanel mainPanel;
	
	private JColorButton backgroundColorButton;
	
	public OptionsWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		
		setTitle("Options");
		
		mainPanel = new JGridPanel(6);
		add(mainPanel);
		
		backgroundColorButton = new JColorButton(Color.white);
		backgroundColorButton.setOnColorChange((source) -> {
			JButton button = (JButton) source;
			if(button.getText().equalsIgnoreCase("ok"))
				mainWindow.getImagePanel().setBackground(backgroundColorButton.getColor());
		});

		mainPanel.add(new JLabel("Background Color: "), 0, 0, 1, 1, 1, 0);
		mainPanel.add(backgroundColorButton, 1, 0, 1, 1, 1, 0);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

}

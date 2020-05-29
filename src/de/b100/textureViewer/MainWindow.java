package de.b100.textureViewer;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.b100.swing.JGridPanel;
import de.b100.swing.SwingUtils;
import de.b100.swing.Window;

public class MainWindow extends Window implements KeyListener, MouseWheelListener{

	private static final long serialVersionUID = 1L;
	
	private FileSelectWindow fileSelectWindow;
	
	private JGridPanel mainPanel;
	
	private ImagePanel imagePanel;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	
	private OptionsWindow optionsWindow;
	
	public MainWindow() {
		mainPanel = new JGridPanel();
		imagePanel = new ImagePanel();
		fileSelectWindow = new FileSelectWindow(this, new File("").getAbsoluteFile());
		optionsWindow = new OptionsWindow(this);
		
		//Get First Image in Folder
		File[] files = new File("").getAbsoluteFile().listFiles();
		for(File file : files) {
			ImageFile imageFile = new ImageFile(file);
			if(imageFile.isValidImage()) {
				imagePanel.setImageFile(imageFile);
				break;
			}
		}
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");

		fileMenu.add(SwingUtils.newJMenuItem("Open", (e) -> fileSelectWindow.setVisible(true)));
		fileMenu.add(SwingUtils.newJMenuItem("Options", (e) -> optionsWindow.setVisible(true)));

		menuBar.add(fileMenu);
		
		mainPanel.add(menuBar, 0, 0, 1, 1, 1, 0);
		mainPanel.add(imagePanel, 0, 1, 1, 1, 1, 1);
		
		add(mainPanel);
		
		Dimension dim1 = new Dimension(640,480);
		mainPanel.setPreferredSize(dim1);
		
		addKeyListener(this);
		addMouseWheelListener(this);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			fileSelectWindow.setVisible(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_R) {
			imagePanel.setRepeat(!imagePanel.isRepeat());
		}
		if(e.getKeyCode() == KeyEvent.VK_F5) {
			imagePanel.getImageFile().reload();
			imagePanel.repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
	
	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		imagePanel.setZoom(imagePanel.getZoom() + e.getWheelRotation());
	}
	
	
	
}

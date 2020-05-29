package de.b100.textureViewer;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.b100.swing.JGridPanel;
import de.b100.swing.Window;

public class FileSelectWindow extends Window implements ListSelectionListener, KeyListener, WindowFocusListener{
	
	private static final long serialVersionUID = 1L;

	private MainWindow mainWindow;
	
	private JGridPanel mainPanel;
	private JScrollPane scrollPane;
	private JList<String> list;
	private ImagePanel preview;
	
	private File folder;
	
	public FileSelectWindow(MainWindow mainWindow, File folder) {
		this.mainWindow = mainWindow;
		
		mainPanel = new JGridPanel();
		list = new JList<String>();
		preview = new ImagePanel();
		scrollPane = new JScrollPane(list);
		
		list.setCellRenderer(new CellRenderer(this));
		list.addListSelectionListener(this);
		list.addKeyListener(this);
		
		mainPanel.setFocusable(false);
		preview.setFocusable(false);
		
		mainPanel.getGridBagConstraints().insets = new Insets(5,5,5,5);
		mainPanel.add(scrollPane, 0, 0);
		mainPanel.add(preview, 1, 0);
		
		add(mainPanel);
		
		addWindowFocusListener(this);
		
		Dimension dim1 = new Dimension(540, 336);
		mainPanel.setPreferredSize(dim1);

		setAlwaysOnTop(true);
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setFolder(folder, null);
	}
	
	public void setFolder(File file, File previousFile) {
		System.out.println("Set Folder: "+file);
		if(!file.isDirectory()) {
			throw new RuntimeException();
		}
		this.folder = file;
		
		ArrayList<String> nameList = new ArrayList<String>();
		
		File parent = file.getParentFile();
		if(parent != null && parent.exists() && parent.isDirectory()) {
			nameList.add("..");
		}
		
		File[] files = file.listFiles();
		for(File f : files) {
			String name = f.getName();
			if(f.isDirectory())name += "/";
			nameList.add(name);
		}

		list.setListData(Utils.toArray(nameList));
		list.setSelectedIndex(0);
		if(previousFile != null) {
			int i=0;
			while(i < nameList.size()) {
				File f = new File(folder, nameList.get(i));
				if(previousFile.getAbsolutePath().equals(f.getAbsolutePath())) {
					list.setSelectedIndex(i);
					System.out.println(i);
				}
				i++;
			}
		}
	}
	
	public File getFolder() {
		return folder;
	}

	public void valueChanged(ListSelectionEvent e) {
		File file;
		try {
			file = new File(getFolder(), list.getSelectedValue());
			preview.setImageFile(new ImageFile(file));
		}catch (Exception ex) {
			preview.setImageFile(null);
		}
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			setVisible(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(list.getSelectedValue().equals("..")) {
				setFolder(getFolder().getParentFile(), getFolder());
			}else {
				File file = new File(getFolder(), list.getSelectedValue());
				
				if(file.isDirectory()) {
					setFolder(file, null);
				}else {
					ImageFile imageFile = new ImageFile(file);
					if(imageFile.isValidImage()) {
						mainWindow.getImagePanel().setImageFile(imageFile);
						setVisible(false);
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void windowGainedFocus(WindowEvent e) {
		
	}

	public void windowLostFocus(WindowEvent e) {
		setVisible(false);
	}
	
}

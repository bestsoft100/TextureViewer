package de.b100.textureViewer;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

public class CellRenderer extends DefaultListCellRenderer{

	private static final long serialVersionUID = 1L;
	
	private FileSelectWindow fileSelectWindow;
	private JLabel label;
	
	public CellRenderer(FileSelectWindow fileSelectWindow) {
		this.fileSelectWindow = fileSelectWindow;
		
		label = new JLabel("<>");
		label.setOpaque(true);
	}
	
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		String val = (String) value;
		
		label.setText(val);
		label.setBackground(super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus).getBackground());
		
		if(val.equals("..")) {
			label.setIcon(new ImageIcon("up.png"));
		}else {
			File file = new File(fileSelectWindow.getFolder(), val);
			if(file.isDirectory()) {
				label.setIcon(new ImageIcon("folder.png"));
			}else {
				ImageFile imageFile = new ImageFile(file);
				if(imageFile.isValidImage()) {
					label.setIcon(new ImageIcon("image.png"));
				}else {
					label.setIcon(new ImageIcon("file.png"));
				}
			}
		}
		
		
		return label;
		
		
		
	}

}

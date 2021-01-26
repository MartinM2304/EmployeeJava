import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;

public class MainFrame extends JFrame {
	
	
	
	private DetailsPanel detailsPanel;
	
	
	public MainFrame (String title) {
		super(title);
		
		
	//set layout
	setLayout (new BorderLayout());	
	
	
	


	detailsPanel = new DetailsPanel();
	
	
	
	getContentPane().add (detailsPanel,BorderLayout.CENTER );
	//detailsPanel.setBackground(Color.GRAY);
	//getContentPane().setBackground(Color.RED);
	
	
	
	
	}
		
			

}

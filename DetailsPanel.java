import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.*;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class DetailsPanel extends JPanel {
	
	private JTable employeeTable;
	DefaultTableModel employeeDefault;
    private JScrollPane employeeScrollPane;
    //Buttons
    private JButton addBtn = new JButton ("Add");
    private JButton deleteBtn = new JButton ("Delete");
    private JButton clearBtn = new JButton ("Clear");
    private JButton saveBtn = new JButton ("Save");
    private JButton loadBtn = new JButton ("Load");
    //Labels
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel companyLabel;
    private JLabel adressLabel;
    private JLabel salaryLabel;
    //Text Fields
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField companyField;
    private JTextField adressField;
    private JTextField salaryField;
    
    private File file;

	public DetailsPanel() {
		
		setPreferredSize(new Dimension(800,800));
		setLayout(null);
		
		//Title
		
		setBorder (BorderFactory.createTitledBorder("Your info"));
		
		//Label
		
		firstNameLabel = new JLabel ("Name:");
		lastNameLabel = new JLabel ("Lastname:");
		companyLabel = new JLabel ("Company:");
		adressLabel = new JLabel ("Adress:");
		salaryLabel = new JLabel ("Salary:");
		
		//Fields
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		companyField = new JTextField(10);
		adressField= new JTextField(15);
		salaryField= new JTextField(10);
		
		//Table
		
		 
		 employeeDefault = new DefaultTableModel();
		 employeeDefault.addColumn("Name");
		 employeeDefault.addColumn("Last Name");
		 employeeDefault.addColumn("Company");
		 employeeDefault.addColumn("Salary");
		 employeeTable = new JTable(employeeDefault);
		 
		 
		 //creating buttons and scroll panel
		 addBtn = new JButton("Add");
	     deleteBtn = new JButton("Delete");
	     clearBtn = new JButton("Clear");
	     saveBtn = new JButton ("Save");
	     loadBtn = new JButton ("Load");
		 employeeScrollPane = new JScrollPane(employeeTable);
		 
		 
		 file = new File("Employee.txt");
		 
		 //adding to panel
		 add(firstNameLabel);
		 add(lastNameLabel);
		 add(companyLabel);
		 add(salaryLabel);
		 add(firstNameField);
		 add(lastNameField);
		 add(companyField);
		 add(salaryField);
		 
		 add(employeeScrollPane);
		 add(addBtn);
		 add(deleteBtn);
		 add(clearBtn);
		 add(saveBtn);
		 add(loadBtn);
		 
		 //adding bounds
		 firstNameLabel.setBounds(60,30,100,30);
		 lastNameLabel.setBounds(310,30,100,30);
		 companyLabel.setBounds(60,90,100,30);
		 salaryLabel.setBounds(310,90,100,30);
		 
		 
		 firstNameField.setBounds(160, 30, 100, 30);
		 lastNameField.setBounds(400, 30,100, 30);
		 companyField.setBounds(160, 90, 100, 30);
		 salaryField.setBounds(400, 90, 100, 30);
		 
		 addBtn.setBounds(60, 160, 100, 50);
		 deleteBtn.setBounds(300, 160, 100, 50);
		 clearBtn.setBounds(540, 160, 100, 50);
		 saveBtn .setBounds(60, 800, 100, 50);
		 loadBtn.setBounds(540, 800, 100, 50);
		 
		 
		 employeeScrollPane.setBounds(125, 255, 435, 285);
		 
		 //creating the Table with Model
		 employeeTable.getSelectionModel().addListSelectionListener(x->{
			 if(employeeTable.getSelectedRow() != -1) {
			 firstNameField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 0).toString());
             lastNameField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 1).toString());
             companyField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 2).toString());
             salaryField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 3).toString());
			 
			 }
		 });
		 
		 
		 
		 
		 addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				double salary = Double.parseDouble(salaryField.getText());
				Employee addNewData = new Employee (firstNameField.getText(), lastNameField.getText(), companyField.getText(),salary);
				employeeDefault.addRow(new Object[] {addNewData.getFirstName(), addNewData.getLastName(),addNewData.getCompany(),addNewData.getSalary()} );
				
				firstNameField.setText ("");
				lastNameField.setText ("");
				companyField.setText ("");
				salaryField.setText ("");
			}
			 
			 
		 });
		 
		 
		 
		 clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				firstNameField.setText ("");
				lastNameField.setText ("");
				companyField.setText ("");
				salaryField.setText ("");
				
			}
			 
			 
		 });
		 
		 deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int rowNumber = employeeTable.getSelectedRow();
				
				employeeDefault.removeRow(rowNumber);
				employeeTable.repaint();
				
				firstNameField.setText ("");
				lastNameField.setText ("");
				companyField.setText ("");
				salaryField.setText ("");
				
			}
			 
		 });
		 //reading the file
		 loadBtn .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedReader reader;
				String line;
				try {
					reader = new BufferedReader(new FileReader(file));
					while ((line = reader.readLine()) != null) {
						employeeTable.add(null, line.split(","));
					}
					reader.close();
	
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
			 
		 });
		 //saving the file
		 saveBtn .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					FileWriter myWriter = new FileWriter("filename.txt",true);
					
					for (int i = 0; i< employeeDefault.getRowCount() ;i++) {
						
						
						for (int j =0; j <employeeDefault.getColumnCount();j++) {
							
							 
			                    myWriter.write(employeeDefault.getValueAt(i, j).toString() + "\n");
			                    myWriter.write("\n");
			                    
						}
					}
					
					myWriter.close();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
			 
		 });
		 

		 }
	
}
	            
		 
//		 deleteBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				double salary = Double.parseDouble(salaryField.getText());
//				int id = (int) employeeDefault.getValueAt(employeeTable.getSelectedRow(),0);
//				int rowNumber = employeeTable.getSelectedRow();
//				Employee updatedNumber = new Employee (id,firstNameField.getText(), lastNameField.getText(), companyField.getText(),salary );
//				
//			}
//			 
//		 });
		 
		 
		 
//		 GridBagConstraints gc = new GridBagConstraints();
//		 
//		 gc.anchor = GridBagConstraints.FIRST_LINE_END;
//			
//			gc.weightx = 1;
//			gc.weighty =1;
//			
//			gc.gridx = 0;
//			gc.gridy = 0;
//			
//			add (firstNameLabel,gc);
//			
//			gc.gridx = 0;
//			gc.gridy = 1;
//			add (lastNameLabel,gc);
//			
//			gc.gridx = 0;
//			gc.gridy = 2;
//			
//			add (adressLabel,gc);
//			
//			gc.gridx = 0;
//			gc.gridy = 3;
//			
//			add (companyLabel,gc);
//			
//			gc.gridx = 0;
//			gc.gridy = 4;
//			add(salaryLabel,gc);
//			
//		//Second column
//			
//			gc.weightx = 1;
//			gc.weighty =1;
//			
//			gc.gridx = 1;
//			gc.gridy = 0;
//			
//			add (firstNameField,gc);
//			
//			gc.gridx = 1;
//			gc.gridy = 1;
//			
//			add (lastNameField,gc);
//			
//			gc.gridx = 1;
//			gc.gridy = 2;
//			
//			add (adressField,gc);
//			
//			gc.gridx = 1;
//			gc.gridy = 3;
//			
//			add (companyField,gc);
//			gc.gridx = 1;
//			gc.gridy = 4;
//			
//			add(salaryField,gc);
//	
//		//Buttons
//			gc.weighty = 1;

//		
//		gc.weighty = 10;
//		
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.gridx = 1;
//		gc.gridy = 4;
//		add (addBtn,gc);
		
  

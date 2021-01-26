import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class DetailsPanel extends JPanel {
	
	private JTable employeeTable;
	DefaultTableModel employeeModel;
    private JScrollPane employeeScrollPane;
    //Buttons
    private JButton addBtn = new JButton ();
    private JButton deleteBtn = new JButton ();
    private JButton clearBtn = new JButton ();
    private JButton saveBtn = new JButton ();
    private JButton loadBtn = new JButton ();
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
    private JNumberTextFieldd salaryField = new JNumberTextFieldd();
    
    
    private File file;

	public DetailsPanel() {
		
		setPreferredSize(new Dimension(650,800));
		
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
		//salaryField= new JTextField(10);
		
		salaryField.setMaxLength(10);
		salaryField.setAllowNegative(false);
		salaryField.setPrecision(1);
		
		//Table
		
		 
		 employeeModel = new DefaultTableModel();
		 employeeModel.addColumn("Name");
		 employeeModel.addColumn("Last Name");
		 employeeModel.addColumn("Company");
		 employeeModel.addColumn("Salary");
		 employeeTable = new JTable(employeeModel);
		 
		 
		 //creating buttons and scroll panel
		 addBtn = new JButton("Add");
	     deleteBtn = new JButton("Delete");
	     clearBtn = new JButton("Clear text");
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
		 //addBtn.setBackground(Color.GREEN);
		 add(deleteBtn);
		 //deleteBtn.setBackground(Color.GREEN);
		 add(clearBtn);
		 //clearBtn.setBackground(Color.GREEN);
		 add(saveBtn);
		 //saveBtn.setBackground(Color.GREEN);
		 add(loadBtn);
		 //loadBtn.setBackground(Color.GREEN);
		 
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
		 saveBtn .setBounds(60, 700, 100, 50);
		 loadBtn.setBounds(540, 700, 100, 50);
		 
		 
		 employeeScrollPane.setBounds(100, 255, 500, 385);
		 
		 //creating the Table with Model
		 //https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableModel.html - Kakvo å TableModel
		 employeeTable.getSelectionModel().addListSelectionListener(x->{
			 if(employeeTable.getSelectedRow() != -1) {
			 firstNameField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 0).toString());
             lastNameField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 1).toString());
             companyField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 2).toString());
             salaryField.setText(employeeTable.getValueAt(employeeTable.getSelectedRow(), 3).toString());
			 
			 }
		 });
		 
		 //adding action to the addBtn (When clicked)
		 addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				double salary = Double.parseDouble(salaryField.getText());
				Employee addNewData = new Employee (firstNameField.getText(),lastNameField.getText(), companyField.getText(),salary);
				employeeModel.addRow(new Object[] {addNewData.getFirstName(), addNewData.getLastName(),addNewData.getCompany(),addNewData.getSalary()} );
				
				
				
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
				
				employeeModel.removeRow(rowNumber);
				
				//refreshes the Table
				employeeTable.repaint();
				
				firstNameField.setText ("");
				lastNameField.setText ("");
				companyField.setText ("");
				salaryField.setText ("");
				
			}
			 
		 });
		 //reading and loading the file
		 loadBtn .addActionListener(new ActionListener() {

				@Override
					public void actionPerformed(ActionEvent e) {
						
							String lineFirstName;
							String lineLastName;
							String lineCompany ;
							String line3;

							
							for (int i = 0; i< employeeModel.getRowCount() ;i++) {
								
								employeeModel.removeRow(i);
							}
								File myObj = new File("filename.txt");
								
								try {
									Scanner myReader = new Scanner(myObj);
									while (myReader.hasNextLine()) {
										lineFirstName = myReader.nextLine();
										lineLastName = myReader.nextLine();
									    lineCompany = myReader.nextLine();
									    line3 = myReader.nextLine();
									    double salary = Double.parseDouble(line3);
										Employee addNewData = new Employee (lineFirstName,lineLastName , lineCompany ,salary);
										employeeModel.addRow(new Object[] {addNewData.getFirstName(), addNewData.getLastName(),addNewData.getCompany(),addNewData.getSalary()} );
										
									}
									myReader.close();
								} catch (FileNotFoundException e2) {
									try {
										myObj.createNewFile();
									} catch (IOException e1) {
										
										e1.printStackTrace();
									}
								
								}
				}
	 
		});
		 //saving the file
		 saveBtn .addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					FileWriter myWriter = new FileWriter("filename.txt",false);
					
					for (int i = 0; i< employeeModel.getRowCount() ;i++) {
						
						
						for (int j =0; j <employeeModel.getColumnCount();j++) {
							
							 
			                    myWriter.write(employeeModel.getValueAt(i, j).toString() + " ");
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
//								try {
//								
//										reader = new BufferedReader(new FileReader(file));
//											while ((line = reader.readLine()) != null) {
//													
//													
//													line1 = reader.readLine();
//													line2 = reader.readLine();
//													line3 = reader.readLine();
//													double salary = Double.parseDouble(line3);
//													Employee addNewData = new Employee (line,line1 , line2,salary);
//													employeeDefault.addRow(new Object[] {addNewData.getFirstName(), addNewData.getLastName(),addNewData.getCompany(),addNewData.getSalary()} );
//													
//												}
//											reader.close();
//
//								} catch (Exception e1) {
//									
//									e1.printStackTrace();
//								}



// 		 private JTextField salaryField;
		



//		 loadBtn .addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					BufferedReader reader;
//					
//					try {
//						
//						reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//						
//						Map<String, Object[]> result = new LinkedHashMap <String,Object[]>();
//						
//						while (reader.ready()) {
//							String line = reader.readLine();
//							String[] values = line.split("\\s+");
//							String key = values[0] +"\t" + values[1];
//							
//							String label = values[0];
//			                String date = values[1];
//			                Integer sum = 0;
//			                Integer count = 0;
//			                if (result.containsKey(key)) {
//			                    sum = (Integer) ((Object[]) result.get(key))[2];
//			                    count = (Integer) ((Object[]) result.get(key))[3];
//			                } else {
//
//			                }
//			                result.put(key, new Object[]{label, date,
//			                        sum + Integer.parseInt(values[2]), count + 1});
//			            }
////			            ArrayList arrayList = new ArrayList(result.values());
//						reader.close();
//						}
//					 catch (Exception e1) {
//						
//						e1.printStackTrace();
//					}
//					
//				}
//				 
//			 });      
		 
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
		 
		 
		 

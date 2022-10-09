import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.sql.SQLException;

class Task{

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JRadioButton rdbtnNewRadioButton_1;
    private JTextField textField_4;
    private JCheckBox chckbxNewCheckBox;
    private JCheckBox chckbxNewCheckBox_1;
    private JCheckBox chckbxNewCheckBox_2;
    private JCheckBox chckbxNewCheckBox_3;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Task window = new Task();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Task() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Name :");
        lblNewLabel.setBounds(67, 11, 46, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Roll No");
        lblNewLabel_1.setBounds(67, 50, 46, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Batch");
        lblNewLabel_2.setBounds(67, 87, 46, 14);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Section");
        lblNewLabel_3.setBounds(67, 132, 46, 14);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Gender");
        lblNewLabel_4.setBounds(67, 178, 46, 14);
        frame.getContentPane().add(lblNewLabel_4);

        ButtonGroup g=new ButtonGroup();
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
        rdbtnNewRadioButton.setBounds(144, 174, 109, 23);
        frame.getContentPane().add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("Female");
        rdbtnNewRadioButton_1.setBounds(261, 174, 109, 23);
        frame.getContentPane().add(rdbtnNewRadioButton_1);

        g.add(rdbtnNewRadioButton);
        g.add(rdbtnNewRadioButton_1);

        textField = new JTextField();
        textField.setBounds(136, 129, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(136, 84, 86, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(136, 47, 86, 20);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(136, 8, 156, 20);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(144, 378, 131, 22);
        comboBox.addItem("Pakistan");
        comboBox.addItem("India");
        comboBox.addItem("China");
        frame.getContentPane().add(comboBox);



        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String Gender;
            	String Qualification="a";
            	String Adress;
            	String Country="";
          	
                JSONObject StudentDetails = new JSONObject();
                StudentDetails.put("Name",textField_3.getText());
                StudentDetails.put("Roll No",textField_2.getText());
                StudentDetails.put("Batch",textField_1.getText());
                StudentDetails.put("Section",textField.getText());
                if(rdbtnNewRadioButton.isSelected()){
                	Gender=rdbtnNewRadioButton.getText();
                    StudentDetails.put("Gender",rdbtnNewRadioButton.getText());}
                else  {
                	Gender=rdbtnNewRadioButton_1.getText();
                    StudentDetails.put("Gender",rdbtnNewRadioButton_1.getText());
                }
                if(chckbxNewCheckBox.isSelected()){
                	Qualification=chckbxNewCheckBox.getText();
                    StudentDetails.put("Qualification",chckbxNewCheckBox.getText());
                }
                else if(chckbxNewCheckBox_1.isSelected()){
                	Qualification=chckbxNewCheckBox_1.getText();
                	StudentDetails.put("Qualification",chckbxNewCheckBox_1.getText());
                }
                else if(chckbxNewCheckBox_2.isSelected()){
                	Qualification=chckbxNewCheckBox_2.getText();
                    StudentDetails.put("Qualification",chckbxNewCheckBox_2.getText());
                }
                else if(chckbxNewCheckBox_3.isSelected()){
                	Qualification=chckbxNewCheckBox_3.getText();
                    StudentDetails.put("Qualification",chckbxNewCheckBox_3.getText());
                }
                StudentDetails.put("Adress",textField_4.getText());
                StudentDetails.put("Country",comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
                 Country=comboBox.getItemAt(comboBox.getSelectedIndex()).toString();
                String name=textField_3.getText();
                String rollNo=textField_2.getText();
                String batch=textField_1.getText();
                String section=textField.getText();
                String address=textField_4.getText();
                JSONObject StudentObject = new JSONObject();
                StudentObject.put("Student", StudentDetails);
                 
                try {
            		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsinformation","root","");
            		
            		String SQL = "INSERT INTO studentsinformation VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            		PreparedStatement pstmt = con.prepareStatement(SQL);
            		pstmt.setString(1, name);
            		pstmt.setString(2, rollNo);
            		pstmt.setString(3, batch);
            		pstmt.setString(4, section);
            		pstmt.setString(5, Gender);
            		pstmt.setString(6, Qualification);
            		pstmt.setString(7, address);
            		pstmt.setString(8, Country);
            		pstmt.executeUpdate();
            		pstmt.close();
            		
            	    	
            	    }
            	    catch(Exception f) {
            	    	JOptionPane.showMessageDialog(null, "Maslo ache tho" + f.getMessage());
            	    }
            	    
                
                try (FileWriter file = new FileWriter("Student.json")) {
                    //We can write any JSONArray or JSONObject instance to the file
                    file.write(StudentObject.toJSONString());
                    file.flush();

                } catch (IOException g) {
                    g.printStackTrace();
                }
                
                
            }
            
        });
        btnNewButton.setBounds(203, 417, 89, 23);
        frame.getContentPane().add(btnNewButton);



        JButton btnNewButton_1 = new JButton("Print");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
                try {
                    Output p=new Output();
                    p.setVisible(true);
                    p.textField.setText(textField_3.getText());
                    p.textField_1.setText(textField_2.getText());
                    p.textField_2.setText(textField_1.getText());
                    p.textField_3.setText(textField.getText());
                    if(rdbtnNewRadioButton.isSelected()){                       
                    	p.textField_4.setText(rdbtnNewRadioButton.getText());}
                    else  {
                        p.textField_4.setText(rdbtnNewRadioButton_1.getText());
                }
                if(chckbxNewCheckBox.isSelected()){
                    p.textField_5.setText(chckbxNewCheckBox.getText());
                }
                else if(chckbxNewCheckBox_1.isSelected()){
                    p.textField_5.setText(chckbxNewCheckBox_1.getText());
                }
                else if(chckbxNewCheckBox_2.isSelected()){
                    p.textField_5.setText(chckbxNewCheckBox_2.getText());
                }
                else if(chckbxNewCheckBox_3.isSelected()){
                    p.textField_5.setText(chckbxNewCheckBox_3.getText());                    }

                p.textField_6.setText(textField_4.getText());
                p.textField_7.setText(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());


            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }	
            	
            }
        });
        btnNewButton_1.setBounds(333, 417, 89, 23);
        frame.getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel_5 = new JLabel("Qualification");
        lblNewLabel_5.setBounds(67, 230, 66, 14);
        frame.getContentPane().add(lblNewLabel_5);

         chckbxNewCheckBox = new JCheckBox("Matric");
        chckbxNewCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            if(chckbxNewCheckBox_1.isSelected()){
                chckbxNewCheckBox_1.setSelected(false);
            }
            if(chckbxNewCheckBox_2.isSelected()){
                chckbxNewCheckBox_2.setSelected(false);
            }

                if(chckbxNewCheckBox_3.isSelected()){
                    chckbxNewCheckBox_3.setSelected(false);
                }
            }
        });
        chckbxNewCheckBox.setBounds(144, 226, 97, 23);
        frame.getContentPane().add(chckbxNewCheckBox);

         chckbxNewCheckBox_1 = new JCheckBox("Intermediate");
        chckbxNewCheckBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(chckbxNewCheckBox.isSelected()){
                    chckbxNewCheckBox.setSelected(false);
                }
                if(chckbxNewCheckBox_2.isSelected()){
                    chckbxNewCheckBox_2.setSelected(false);
                }

                if(chckbxNewCheckBox_3.isSelected()){
                    chckbxNewCheckBox_3.setSelected(false);
                }
            }
        });
        chckbxNewCheckBox_1.setBounds(261, 226, 97, 23);
        frame.getContentPane().add(chckbxNewCheckBox_1);

         chckbxNewCheckBox_2 = new JCheckBox("Graduate");
        chckbxNewCheckBox_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(chckbxNewCheckBox_1.isSelected()){
                    chckbxNewCheckBox_1.setSelected(false);
                }
                if(chckbxNewCheckBox.isSelected()){
                    chckbxNewCheckBox.setSelected(false);
                }

                if(chckbxNewCheckBox_3.isSelected()){
                    chckbxNewCheckBox_3.setSelected(false);
                }
            }
        });
        chckbxNewCheckBox_2.setBounds(144, 264, 97, 23);
        frame.getContentPane().add(chckbxNewCheckBox_2);

         chckbxNewCheckBox_3 = new JCheckBox("Post Graduate");
        chckbxNewCheckBox_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(chckbxNewCheckBox_1.isSelected()){
                    chckbxNewCheckBox_1.setSelected(false);
                }
                if(chckbxNewCheckBox_2.isSelected()){
                    chckbxNewCheckBox_2.setSelected(false);
                }

                if(chckbxNewCheckBox.isSelected()){
                    chckbxNewCheckBox.setSelected(false);
                }
            }
        });
        chckbxNewCheckBox_3.setBounds(261, 264, 97, 23);
        frame.getContentPane().add(chckbxNewCheckBox_3);

        JLabel lblNewLabel_6 = new JLabel("Address");
        lblNewLabel_6.setBounds(67, 309, 46, 14);
        frame.getContentPane().add(lblNewLabel_6);

        textField_4 = new JTextField();
        textField_4.setBounds(144, 306, 370, 61);
        frame.getContentPane().add(textField_4);
        textField_4.setColumns(10);


        JLabel lblNewLabel_7 = new JLabel("Country");
        lblNewLabel_7.setBounds(67, 382, 46, 14);
        frame.getContentPane().add(lblNewLabel_7);
        frame.setBounds(100, 100, 660, 490);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



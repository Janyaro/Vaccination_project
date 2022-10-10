// import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
// import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
// import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Output extends JFrame {

    private JPanel contentPane;
    public JTextField textField;
    public JTextField textField_1;
    public JTextField textField_2;
    public JTextField textField_3;
    public JTextField textField_4;
    public JTextField textField_5;
    public JTextField textField_6;
    public JTextField textField_7;
    // Task t=new Task();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Output frame = new Output();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Output() throws FileNotFoundException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 490);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 140, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(147, 11, 46, 14);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(228, 11, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(228, 53, 86, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(228, 104, 86, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(228, 163, 86, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(228, 213, 86, 20);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(228, 267, 86, 20);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(228, 317, 338, 92);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setBounds(228, 420, 86, 20);
        contentPane.add(textField_7);
        textField_7.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Roll No");
        lblNewLabel_1.setBounds(147, 56, 46, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("batch");
        lblNewLabel_2.setBounds(147, 107, 46, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Gender");
        lblNewLabel_3.setBounds(147, 166, 46, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Section");
        lblNewLabel_4.setBounds(147, 216, 46, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Qualification");
        lblNewLabel_5.setBounds(147, 270, 71, 14);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Adress");
        lblNewLabel_6.setBounds(147, 320, 46, 14);
        contentPane.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("Country");
        lblNewLabel_7.setBounds(147, 423, 46, 14);
        contentPane.add(lblNewLabel_7);


    }}

package GUI;
import DataBase.Account;
import DataBase.AccountDB;
import SocketStuff.Client;
import SocketStuff.Threads.OutputStream;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class SignUp extends JPanel
{
    private JLabel firstName;
    private JTextField firstNameField;
    private String strFirstName;

    private JLabel lastName;
    private JTextField lastNameField;
    private String strLastName;

    private JLabel email;
    private String strEmail;
    private JTextField emailField;

    private JLabel userName;
    private JTextField userNameField;
    private String strUserName;

    private JLabel passWord;
    private JPasswordField passWordField;
    private String strPassWord;

    private FileInputStream fileInput;
    private String picturePath;

    private JButton regBtn;
    private JButton picBtn;

    private JLabel haveAccount;
    private JButton loginBtn;

    private JFileChooser fileChooser;
    private static JFrame frame;


    public SignUp() {
        //construct components

        firstName = new JLabel ("FirstName:");
        lastName = new JLabel ("LastName:");
        email = new JLabel ("Email:");
        userName = new JLabel ("UserName:");
        passWord = new JLabel ("PassWord:");
        picBtn = new JButton ("Choose Picture");
        regBtn = new JButton ("Register");
        firstNameField = new JTextField (5);
        lastNameField = new JTextField (5);
        emailField = new JTextField (5);
        userNameField = new JTextField (5);
        passWordField = new JPasswordField (5);
        fileChooser = new JFileChooser();
        haveAccount = new JLabel ("Already Have Account");
        loginBtn = new JButton ("Login");


        //adjust size and set layout
        setPreferredSize (new Dimension(389, 341));
        setLayout (null);


        picBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int x = fileChooser.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    copy();
                }
            }
            public void copy()
            {
                try
                {
                    File f1 = fileChooser.getSelectedFile();
                    picturePath = f1.getAbsolutePath();
                    System.out.println(picturePath);
                    fileInput = new FileInputStream(f1.getAbsolutePath());
                }
                catch (FileNotFoundException error)
                {
                    System.err.println(error);
                }
            }
        });


        regBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                strFirstName = userNameField.getText();
                strLastName = lastNameField.getText();
                strEmail = emailField.getText();
                strUserName = userNameField.getText();
                strPassWord = String.valueOf(passWordField.getPassword());
                Client.setInfo(strUserName,strPassWord);
                OutputStream.send("##AddUser-"+
                        strFirstName+"-"+
                        strLastName+"-"+
                        strEmail+"-"+
                        strUserName+"-"+
                        strPassWord+"-"+
                        picturePath);
            }
        });

        loginBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                WelcomePage.HideSignUp();
                frame = new JFrame ("Log In");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new LogIn());
                frame.pack();
                frame.setVisible (true);
            }
        });
        //add components
        add (regBtn);
        add (firstName);
        add (lastName);
        add (email);
        add (userName);
        add (passWord);
        add (picBtn);
        add (firstNameField);
        add (lastNameField);
        add (emailField);
        add (userNameField);
        add (passWordField);
        add (haveAccount);
        add (loginBtn);

        //set component bounds (only needed by Absolute Positioning)
        regBtn.setBounds (145, 295, 100, 20);
        firstName.setBounds (20, 25, 100, 25);
        lastName.setBounds (20, 65, 100, 25);
        email.setBounds (20, 105, 100, 25);
        userName.setBounds (20, 145, 100, 25);
        passWord.setBounds (20, 185, 100, 25);
        picBtn.setBounds (20, 245, 130, 25);
        firstNameField.setBounds (90, 25, 125, 25);
        lastNameField.setBounds (90, 65, 125, 25);
        emailField.setBounds (90, 105, 275, 25);
        userNameField.setBounds (90, 145, 150, 25);
        passWordField.setBounds (90, 185, 150, 25);
        haveAccount.setBounds (130, 350, 130, 25);
        loginBtn.setBounds (145, 380, 100, 25);

    }
}
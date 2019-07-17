package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.OutputStream;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    private String pictureName = "##default.png";
    private Path path1;

    private JButton regBtn;
    private JButton picBtn;
    private JLabel picDec;

    private JLabel haveAccount;
    private JButton loginBtn;
    private JLabel message;

    private JFileChooser fileChooser;

    public SignUp()
    {
        //construct components
        firstName = new JLabel ("FirstName:");
        lastName = new JLabel ("LastName:");
        email = new JLabel ("Email:");
        userName = new JLabel ("UserName:");
        passWord = new JLabel ("PassWord:");
        picBtn = new JButton ("Choose Picture");
        picDec = new JLabel("Picture scale should be 1 X 1.");
        regBtn = new JButton ("Register");
        firstNameField = new JTextField ("",5);
        lastNameField = new JTextField ("",5);
        emailField = new JTextField ("",5);
        userNameField = new JTextField ("",5);
        passWordField = new JPasswordField ("",5);
        fileChooser = new JFileChooser();
        haveAccount = new JLabel ("Already Have Account?");
        loginBtn = new JButton ("Login");
        message = new JLabel("");



        //adjust size and set layout
        setPreferredSize (new Dimension(400, 425));
        setLayout (null);
        message.setVisible(false);


        picBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int x = fileChooser.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    File f1 = fileChooser.getSelectedFile();
                    path1 = Paths.get(f1.getAbsolutePath());
                    pictureName = f1.getName();
                }
            }
        });


        regBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                strFirstName = firstNameField.getText();
                strLastName = lastNameField.getText();
                strEmail = emailField.getText();
                strUserName = userNameField.getText();
                strPassWord = String.valueOf(passWordField.getPassword());
                if (strPassWord.equals("") || strUserName.equals(""))
                {
                    System.out.println();
                    message.setText("Password and username are essential!");
                    message.setBounds(65, 300, 270, 25);
                    message.setVisible(true);

                }
                else
                {
                    if (!pictureName.equals("##default.png"))
                    {
                        Path path2 = Paths.get("./src/DataBase/ProfilePicture/"+userName+".jpg");
                        try
                        {
                            Files.copy(path1,path2, StandardCopyOption.REPLACE_EXISTING);
                        }
                        catch (IOException error)
                        {
                            System.err.println(error);
                        }
                        pictureName=strUserName;
                    }

                    Client.setInfo(strUserName,strPassWord);
                    OutputStream.send("##AddUser-"+
                            strFirstName+"-"+
                            strLastName+"-"+
                            strEmail+"-"+
                            strUserName+"-"+
                            strPassWord+"-"+
                            pictureName+"-"+
                            "Offline");
                    message.setText("Account created successfully!");
                    message.setBounds (100, 300, 220, 25);
                    message.setVisible(true);
                }
            }
        });

        loginBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                StartGUI.frame.setVisible(false);
                StartGUI.frame.setTitle("Log In");
                StartGUI.frame.getContentPane().removeAll();
                StartGUI.frame.getContentPane().add (new LogIn());
                StartGUI.frame.pack();
                StartGUI.frame.setLocationRelativeTo(null);
                StartGUI.frame.setVisible (true);
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
        add(message);
        add(picDec);

        //set component bounds (only needed by Absolute Positioning)
        regBtn.setBounds (145, 265, 100, 20);
        firstName.setBounds (20, 25, 100, 25);
        lastName.setBounds (20, 65, 100, 25);
        email.setBounds (20, 105, 100, 25);
        userName.setBounds (20, 145, 100, 25);
        passWord.setBounds (20, 185, 100, 25);
        picDec.setBounds(20,225,220,25);
        picBtn.setBounds (240, 225, 150, 25);
        firstNameField.setBounds (110, 25, 125, 25);
        lastNameField.setBounds (110, 65, 125, 25);
        emailField.setBounds (110, 105, 275, 25);
        userNameField.setBounds (110, 145, 150, 25);
        passWordField.setBounds (110, 185, 150, 25);
        haveAccount.setBounds (120, 350, 190, 25);
        loginBtn.setBounds (145, 380, 100, 25);
    }
}
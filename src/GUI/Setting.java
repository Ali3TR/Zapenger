package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.InputStream;
import SocketStuff.Threads.OutputStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Setting extends JPanel
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

    private JLabel passWord;
    private JPasswordField passWordField;
    private String strPassWord;

    private String pictureName = " ";
    private Path path1;

    private JButton change;
    private JButton picBtn;
    private JLabel picDec;

    private JButton logOutBtn;
    private JLabel message;

    private JFileChooser fileChooser;
    private JButton back;
    private static JFrame frame;
    public Setting(boolean mainPageCalledByChatPage)
    {
        firstName = new JLabel ("FirstName:");
        lastName = new JLabel ("LastName:");
        email = new JLabel ("Email:");
        passWord = new JLabel ("PassWord:");
        picBtn = new JButton ("Choose Picture");
        picDec = new JLabel("Picture scale should be 1 X 1.");
        change = new JButton ("Change");
        firstNameField = new JTextField (" ",5);
        lastNameField = new JTextField (" ",5);
        emailField = new JTextField (" ",5);
        passWordField = new JPasswordField ("",5);
        fileChooser = new JFileChooser();
        logOutBtn = new JButton ("Log Out");
        message = new JLabel("");
        back = new JButton ("Back");

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

        change.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                strFirstName = firstNameField.getText();
                strLastName = lastNameField.getText();
                strEmail = emailField.getText();
                strPassWord = String.valueOf(passWordField.getPassword());

                if (!pictureName.equals(" "))
                {
                    Path path2 = Paths.get("./src/DataBase/ProfilePicture/"+Client.getUserName()+".jpg");
                    try
                    {
                        Files.copy(path1,path2, StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException error)
                    {
                        System.err.println(error);
                    }
                    pictureName=Client.getUserName();
                }

                if (strPassWord.equals(""))
                    strPassWord=" ";
                OutputStream.send("##UpdateUser-"+
                        strFirstName+"-"+
                        strLastName+"-"+
                        strEmail+"-"+
                        strPassWord+"-"+
                        pictureName);
                message.setText("tasks are done successfully!");
                message.setBounds (100, 300, 220, 25);
                message.setVisible(true);

            }
        });

        back.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                MainPage.Hide();
            }
        });

        logOutBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OutputStream.send("##SetStatus-"+Client.getUserName());
                MainPage.Hide();
                if (mainPageCalledByChatPage)
                    ChatPage.Hide();
                else
                    LogIn.Hide();
                Client.setInfo("temp","temp");
                frame = new JFrame ("Zapenger");
                frame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        OutputStream.send("##Close");
                        OutputStream.close();
                        InputStream.close();
                        Client.close();
                        e.getWindow().dispose();
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });
                frame.getContentPane().add (new WelcomePage(true));
                frame.pack();
                frame.setVisible (true);
            }
        });


        add (change);
        add (firstName);
        add (lastName);
        add (email);
        add (passWord);
        add (picBtn);
        add (firstNameField);
        add (lastNameField);
        add (emailField);
        add (passWordField);
        add (back);
        add (logOutBtn);
        add(message);
        add(picDec);

        //set component bounds (only needed by Absolute Positioning)
        change.setBounds (145, 265, 100, 20);
        firstName.setBounds (20, 50, 100, 25);
        lastName.setBounds (20, 90, 100, 25);
        email.setBounds (20, 130, 100, 25);
        passWord.setBounds (20, 170 ,100, 25);
        picDec.setBounds(20,210,220,25);
        picBtn.setBounds (240, 210, 150, 25);
        firstNameField.setBounds (110, 50, 125, 25);
        lastNameField.setBounds (110, 90, 125, 25);
        emailField.setBounds (110, 130, 275, 25);
        passWordField.setBounds (110, 170 , 150, 25);
        back.setBounds (2, 2, 70, 25);
        logOutBtn.setBounds (145, 380, 100, 25);

    }
    public static void Hide()
    {
        frame.setVisible(false);
    }
}

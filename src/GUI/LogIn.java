package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.InputStream;
import SocketStuff.Threads.OutputStream;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LogIn extends JPanel
{
    private JButton logIn;
    private JLabel userName;
    private JTextField userNameText;
    private JLabel pass;
    private JPasswordField passText;
    private JLabel noAccount;
    private JButton signUp;
    private String userNameEntered;
    private String passEntered;
    private JLabel incorrect;
    private JLabel notFound;
    private JLabel fieldEmpty;

    public LogIn()
    {
        logIn = new JButton ("Log In");
        userName = new JLabel ("UserName:");
        userNameText = new JTextField (50);
        pass = new JLabel ("Password:");
        passText = new JPasswordField (50);
        noAccount = new JLabel ("Don't have an account? Sign up.");
        signUp = new JButton ("Sign Up");
        incorrect = new JLabel ("Wrong Password!");
        notFound = new JLabel ("Username not found!");
        fieldEmpty = new JLabel ("Fill All the Fields!");

        incorrect.setVisible(false);
        notFound.setVisible(false);
        fieldEmpty.setVisible(false);

        setPreferredSize (new Dimension (258, 249));
        setLayout (null);

        add (logIn);
        add (userName);
        add (userNameText);
        add (pass);
        add (passText);
        add (noAccount);
        add (signUp);
        add (incorrect);
        add (notFound);
        add(fieldEmpty);

        logIn.setBounds (80, 155, 100, 20);
        userName.setBounds (20, 10, 220, 25);
        userNameText.setBounds (20, 35, 220, 30);
        pass.setBounds (20, 80, 215, 25);
        passText.setBounds (20, 105, 220, 30);
        noAccount.setBounds (15, 200, 225, 15);
        signUp.setBounds (85, 220, 90, 20);
        incorrect.setBounds (70, 170, 125, 30);
        notFound.setBounds (57, 170, 151, 30);
        fieldEmpty.setBounds(67,170,151,30);




        logIn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                userNameEntered = userNameText.getText();
                passEntered = String.valueOf(passText.getPassword());
                if (userNameEntered.equals("") || passEntered.equals(""))
                {
                    fieldEmpty.setVisible(true);

                }
                else
                {
                    Client.setInfo(userNameEntered,passEntered);
                    int authorize = Client.isAuthorized();
                    switch (authorize)
                    {
                        case -1:
                            notFound.setVisible(false);
                            incorrect.setVisible(true);
                            fieldEmpty.setVisible(false);
                            break;

                        case 0:
                            incorrect.setVisible(false);
                            notFound.setVisible(true);
                            fieldEmpty.setVisible(false);
                            break;

                        case 1:
                            setVisible(false);
                            StartGUI.frame.setVisible(false);
                            OutputStream.send("##SetStatus-Online");
                            StartGUI.frame.setTitle("Zapenger");
                            StartGUI.frame.getContentPane().removeAll();
                            StartGUI.frame.getContentPane().add (new MainPage(false));
                            StartGUI.frame.pack();
                            StartGUI.frame.setLocationRelativeTo(null);
                            StartGUI.frame.setVisible (true);
                    }
                }
            }
        });

        signUp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                StartGUI.frame.setVisible(false);
                StartGUI.frame.setTitle("Sign Up");
                StartGUI.frame.getContentPane().removeAll();
                StartGUI.frame.getContentPane().add (new SignUp());
                StartGUI.frame.pack();
                StartGUI.frame.setLocationRelativeTo(null);
                StartGUI.frame.setVisible (true);
            }
        });
    }
}

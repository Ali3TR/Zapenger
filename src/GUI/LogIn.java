package GUI;

import SocketStuff.Client;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import javax.swing.*;

public class LogIn extends JPanel
{
    private static JFrame frame;
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



    public LogIn(boolean calledBySignUp)
    {
        //construct components
        logIn = new JButton ("Log In");
        userName = new JLabel ("UserName:");
        userNameText = new JTextField (50);
        pass = new JLabel ("Password:");
        passText = new JPasswordField (50);
        noAccount = new JLabel ("Don't have an account? Sign up.");
        signUp = new JButton ("Sign Up");
        incorrect = new JLabel ("Wrong Password!");
        notFound = new JLabel ("Username not found!");


        incorrect.setVisible(false);
        notFound.setVisible(false);

        //adjust size and set layout
        setPreferredSize (new Dimension (258, 249));
        setLayout (null);

        //add components
        add (logIn);
        add (userName);
        add (userNameText);
        add (pass);
        add (passText);
        add (noAccount);
        add (signUp);
        add (incorrect);
        add (notFound);

        //set component bounds (only needed by Absolute Positioning)
        logIn.setBounds (80, 155, 100, 20);
        userName.setBounds (20, 10, 220, 25);
        userNameText.setBounds (20, 35, 220, 30);
        pass.setBounds (20, 80, 215, 25);
        passText.setBounds (20, 105, 220, 30);
        noAccount.setBounds (15, 200, 225, 15);
        signUp.setBounds (85, 220, 90, 20);
        incorrect.setBounds (70, 165, 125, 30);
        notFound.setBounds (57, 165, 151, 30);




        logIn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                userNameEntered = userNameText.getText();
                passEntered = String.valueOf(passText.getPassword());
                Client.setInfo(userNameEntered,passEntered);
                int authorize = Client.isAuthorized();
                switch (authorize)
                {
                    case -1:
                        notFound.setVisible(false);
                        incorrect.setVisible(true);
                        break;
                    case 0:
                        incorrect.setVisible(false);
                        notFound.setVisible(true);
                        break;
                    case 1:
                        setVisible(false);
                        WelcomePage.Hide();
                        frame = new JFrame ("Zapenger");
                        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().add (new MainPage(false));
                        frame.pack();
                        frame.setVisible (true);
                }

            }
        });
        signUp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                if (calledBySignUp)
                    SignUp.Hide();
                else
                    WelcomePage.Hide();
                frame = new JFrame ("SignUp");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new SignUp(true));
                frame.pack();
                frame.setVisible (true);
            }
        });
    }
    public static void Hide()
    {
        frame.setVisible(false);
    }
}

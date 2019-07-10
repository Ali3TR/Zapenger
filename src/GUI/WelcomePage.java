package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class WelcomePage extends JPanel
{
    private JButton logIn;
    private JButton signUp;
    private JLabel welcome;
    private JLabel welcome2;
    private static JFrame frameLogin;
    private static JFrame frameSignUp;

    public WelcomePage() {
        //construct components
        logIn = new JButton("Log In");
        signUp = new JButton("Sign Up");
        welcome = new JLabel("Hi!");
        welcome2 = new JLabel("Welcome to Zapenger.");

        //adjust size and set layout
        setPreferredSize(new Dimension(258, 175));
        setLayout(null);

        //add components
        add(logIn);
        add(signUp);
        add(welcome);
        add(welcome2);

        //set component bounds (only needed by Absolute Positioning)
        logIn.setBounds(80, 80, 100, 20);
        signUp.setBounds(80, 120, 100, 20);
        welcome.setBounds(20, 10, 220, 25);
        welcome2.setBounds(20, 35, 220, 25);

        //add function to buttons
        logIn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                StartGUI.Hide();
                frameLogin = new JFrame ("Log In");
                frameLogin.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frameLogin.getContentPane().add (new LogIn());
                frameLogin.pack();
                frameLogin.setVisible (true);
            }
        });

        signUp.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                StartGUI.Hide();
                frameLogin = new JFrame ("Sign Up");
                frameLogin.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frameLogin.getContentPane().add (new SignUp());
                frameLogin.pack();
                frameLogin.setVisible (true);
            }
        });

    }
    public static void HideLogin()
    {
        frameLogin.setVisible(false);
    }
    public static void HideSignUp()
    {
        frameSignUp.setVisible(false);
    }
}
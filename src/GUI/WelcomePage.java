package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomePage extends JPanel
{
    private JButton logIn;
    private JButton signUp;
    private JLabel welcome;
    private JLabel welcome2;

    public WelcomePage()
    {
        logIn = new JButton("Log In");
        signUp = new JButton("Sign Up");
        welcome = new JLabel("Hi!");
        welcome2 = new JLabel("Welcome to Zapenger.");

        setPreferredSize(new Dimension(258, 175));
        setLayout(null);

        add(logIn);
        add(signUp);
        add(welcome);
        add(welcome2);

        logIn.setBounds(80, 80, 100, 20);
        signUp.setBounds(80, 120, 100, 20);
        welcome.setBounds(20, 10, 220, 25);
        welcome2.setBounds(20, 35, 220, 25);

        logIn.addActionListener(new ActionListener()
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
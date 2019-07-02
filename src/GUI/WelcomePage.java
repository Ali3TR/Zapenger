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
    private static JFrame frame;

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
                frame = new JFrame ("Log In");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new LogIn());
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

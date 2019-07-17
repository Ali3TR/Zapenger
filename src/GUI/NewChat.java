package GUI;

import SocketStuff.Client;
import SocketStuff.Main;
import SocketStuff.Threads.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewChat extends JPanel
{
    private JButton start;
    private JLabel des;
    private JTextField userNameField;
    private JLabel error;

    public NewChat()
    {
        start = new JButton ("Start");
        des = new JLabel ("Enter username :");
        userNameField = new JTextField ("",5);
        error = new JLabel ("Username not found!");
        error.setVisible(false);

        setPreferredSize (new Dimension (318, 147));
        setLayout (null);

        add (start);
        add (des);
        add (userNameField);
        add (error);

        start.setBounds (110, 85, 100, 20);
        des.setBounds (10, 5, 235, 25);
        userNameField.setBounds (5, 40, 300, 30);
        error.setBounds (90, 110, 175, 30);

        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                MainPage.frame.setVisible(false);
                MainPage.frame.dispose();
                StartGUI.frame.setVisible(false);
                String temp = userNameField.getText();
                OutputStream.send("##StartChat-"+temp);
                if (InputStream.read().equals("Found"))
                {
                    StartGUI.frame.setTitle("Zapenger");
                    StartGUI.frame.getContentPane().removeAll();
                    StartGUI.frame.getContentPane().add (new ChatPage(temp));
                    StartGUI.frame.pack();
                    StartGUI.frame.setLocationRelativeTo(null);
                    StartGUI.frame.setVisible (true);
                }
                else
                {
                    error.setVisible(true);
                }
            }
        });
    }
}

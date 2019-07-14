package GUI;

import SocketStuff.Client;
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
    private static JFrame frame;

    public NewChat(boolean mainPageCalledByChatPage)
    {
        //construct components
        start = new JButton ("Start");
        des = new JLabel ("Enter username :");
        userNameField = new JTextField ("",5);
        error = new JLabel ("Username not found!");
        error.setVisible(false);

        //adjust size and set layout
        setPreferredSize (new Dimension (318, 147));
        setLayout (null);

        //add components
        add (start);
        add (des);
        add (userNameField);
        add (error);

        //set component bounds (only needed by Absolute Positioning)
        start.setBounds (110, 85, 100, 20);
        des.setBounds (10, 5, 235, 25);
        userNameField.setBounds (5, 40, 300, 30);
        error.setBounds (90, 110, 175, 30);

        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainPage.Hide();
                if (mainPageCalledByChatPage)
                    ChatPage.Hide();
                else
                    LogIn.Hide();
                String temp = userNameField.getText();
                OutputStream.send("##StartChat-"+temp);
                if (InputStream.read().equals("Found"))
                {
                    frame = new JFrame ("Zapenger");
                    frame.addWindowListener(new WindowAdapter()
                    {
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            OutputStream.send("##Close");
                            InputGUI.end();
                            GetStatus.end();
                            OutputGUI.end();
                            OutputStream.close();
                            InputStream.close();
                            Client.close();
                            e.getWindow().dispose();
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                    });
                    frame.getContentPane().add (new ChatPage(temp,false));
                    frame.pack();
                    frame.setVisible (true);
                }
                else
                {
                    error.setVisible(true);
                }
            }
        });
    }
    public static void Hide()
    {
        frame.setVisible(false);
    }

}

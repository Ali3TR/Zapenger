package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.InputGUI;
import SocketStuff.Threads.InputStream;
import SocketStuff.Threads.OutputStream;
import SocketStuff.Threads.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatPage extends JPanel
{
    private static JFrame frame;
    private JButton send;
    private JTextArea chats;
    private JButton back;
    private JTextField urChats;
    private JButton sendFile;
    private JButton profilePic;
    private JLabel name;
    private JLabel status;

    public ChatPage(String userName)
    {
        //construct components
        send = new JButton ("Send");
        chats = new JTextArea (5, 5);
        back = new JButton ("Back");
        urChats = new JTextField (5);
        sendFile = new JButton ("Send File");
        profilePic = new JButton ("");
        name = new JLabel (userName);
        status = new JLabel ("NotSetYet");



        //adjust size and set layout
        setPreferredSize (new Dimension(500, 502));
        setLayout (null);

        //add components
        add (send);
        add (chats);
        add (back);
        add (urChats);
        add (sendFile);
        add (profilePic);
        add (name);
        add (status);

        //set component bounds (only needed by Absolute Positioning)
        send.setBounds (355, 475, 145, 25);
        chats.setBounds (0, 55, 500, 385);
        back.setBounds (0, 0, 100, 25);
        urChats.setBounds (0, 445, 500, 25);
        sendFile.setBounds (0, 475, 100, 25);
        profilePic.setBounds (155, 0, 55, 55);
        name.setBounds (210, 0, 165, 25);
        status.setBounds (210, 30, 165, 25);

        chats.setEditable(false);

        InputGUI inputGUI = new InputGUI(chats,userName,status);
        inputGUI.start();
        Status status1 = new Status(userName);
        status1.start();
        back.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                status1.end();
                inputGUI.end();
                OutputStream.send("##Status-"+userName);
                setVisible(false);
                MainPage.Hide();
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
                frame.getContentPane().add (new MainPage(true));
                frame.pack();
                frame.setVisible (true);
            }
        });

        send.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                OutputStream.send("##AddToChat-"+userName+"-"+ Client.getUserName()+"-"
                        +Client.getUserName()+"##"+urChats.getText());
                chats.setEditable(true);
                chats.append("You : "+urChats.getText());
                chats.append("\n");
                chats.setEditable(false);
                urChats.setText("");
            }
        });
    }
    public static void Hide()
    {
        frame.setVisible(false);
    }
}

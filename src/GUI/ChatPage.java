package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChatPage extends JPanel
{
    private static JFrame frame;
    private JButton send;
    private JTextArea chats;
    private JButton back;
    private JTextField urChats;
    private JButton sendFile;
    private JLabel profilePic;
    private JLabel name;
    private JLabel status;
    private BufferedImage img;
    private Image dimg;
    private ImageIcon imageIcon;
    private JScrollPane jScrollPane;

    public ChatPage(String userName,boolean calledByMainPage)
    {
//        try
//        {
//            myPicture = ImageIO.read(new File("./src/DataBase/ProfilePicture/default.png"));
//        }
//        catch (IOException error)
//        {
//            System.err.println(error);
//        }
        //construct components
        try
        {
            img = ImageIO.read(new File("./src/DataBase/ProfilePicture/##default.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        dimg = img.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(dimg);

        send = new JButton ("Send");
        chats = new JTextArea (5, 5);
        back = new JButton ("Back");
        urChats = new JTextField (5);
        sendFile = new JButton ("Send File");
        name = new JLabel (userName);
        status = new JLabel ("NotSetYet");
        profilePic = new JLabel(imageIcon);
        jScrollPane = new JScrollPane(chats);


        //adjust size and set layout
        setPreferredSize (new Dimension(500, 502));
        setLayout (null);

        //add components
        add (send);
        add (jScrollPane);
        add (back);
        add (urChats);
        add (sendFile);
        add (name);
        add (status);
        add(profilePic);

        //set component bounds (only needed by Absolute Positioning)
        send.setBounds (355, 475, 145, 25);
        jScrollPane.setBounds (0, 55, 500, 385);
        back.setBounds (0, 0, 100, 25);
        urChats.setBounds (0, 445, 500, 25);
        sendFile.setBounds (0, 475, 100, 25);
        profilePic.setBounds (155, 0, 55, 55);
        name.setBounds (210, 0, 165, 25);
        status.setBounds (210, 30, 165, 25);


        chats.setEditable(false);

        jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum());

        InputGUI inputGUI = new InputGUI(chats,userName,status);
        OutputGUI outputGUI = new OutputGUI(userName);
        GetStatus status1 = new GetStatus();
        inputGUI.start();
        outputGUI.start();
        status1.start();


        back.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                status1.end();
                inputGUI.end();
                OutputStream.send("##ChatWith-"+Client.getUserName());
                setVisible(false);
                if (calledByMainPage)
                    MainPage.Hide();
                else
                    NewChat.Hide();
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
                frame.setLocationRelativeTo(null);
                frame.setVisible (true);
                OutputGUI.sendOnline();
            }
        });

        send.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String temp =urChats.getText();
                jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum());
                OutputGUI.sendOnline();
                if (!(temp.length()==0))
                {
                    OutputStream.send("##AddToChat-"+userName+"-"+ Client.getUserName()+"-"
                            +Client.getUserName()+"##"+temp);
                    chats.setEditable(true);
                    chats.append("You : "+temp);
                    chats.append("\n");
                    chats.setEditable(false);
                    urChats.setText("");
                }

            }
        });

        urChats.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                OutputGUI.sendTyping();
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                OutputGUI.sendOnline();
            }
        });
    }
    public static void Hide()
    {
        frame.setVisible(false);
    }
}

package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainPage extends JPanel
{
    public static JFrame frame;
    private JButton setting;
    private JButton newChat;
    private ArrayList<JButton> buttons= new ArrayList<>();

    public MainPage(boolean calledByChatPage)
    {
        setting = new JButton ("Setting");
        newChat = new JButton ("Start a new chat");

        loadChats(calledByChatPage);

        //adjust size and set layout
        setPreferredSize (new Dimension(313, 375));
        setLayout (null);

        //add components
        add (setting);
        add (newChat);


        //set component bounds (only needed by Absolute Positioning)
        setting.setBounds (0, 0, 155, 25);
        newChat.setBounds (160, 0, 155, 25);

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame = new JFrame ("Setting");
                frame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        e.getWindow().setVisible(false);
                        e.getWindow().dispose();
                    }
                });
                frame.getContentPane().add (new Setting());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible (true);
            }
        });

        newChat.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame = new JFrame("Start a new Chat");
                frame.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        e.getWindow().setVisible(false);
                        e.getWindow().dispose();
                    }
                });
                frame.getContentPane().add(new NewChat());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    public static void Hide()
    {
        frame.setVisible(false);
    }
    public void loadChats(boolean calledByChatPage)
    {
        OutputStream.send("##ChatWith-"+Client.getUserName());
        String receiveMessage;
        while (true)
        {
            receiveMessage = InputStream.read();
            if (receiveMessage.startsWith("##ChatWith-"))
            {
                if (receiveMessage.split("-").length>1)
                    receiveMessage = receiveMessage.split("-")[1];
                else
                    receiveMessage="";
                break;
            }
        }

        ArrayList<String> listOfChats = new ArrayList<>();
        System.out.println("message received : "+receiveMessage);
        String[] temp = receiveMessage.split("##");
        if (!receiveMessage.equals(""))
            for (int k=0;k<temp.length;k++)
                listOfChats.add(temp[k]);
        for (int k =0;k<listOfChats.size();k++)
            buttons.add(new JButton (listOfChats.get(k)));
        for (int k=0;k<buttons.size();k++)
            add(buttons.get(k));
        for (int k=0;k<buttons.size();k++)
            buttons.get(k).setBounds (0, 45+k*27, 315, 25);
        for (int k=0;k<buttons.size();k++)
        {
            String name = listOfChats.get(k);
            buttons.get(k).addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setVisible(false);
                    if (calledByChatPage)
                    {
                        InputGUI.con();
                        GetStatus.con();
                        OutputGUI.con();
                    }
                    StartGUI.frame.setResizable(true);
                    StartGUI.frame.setVisible(false);
                    StartGUI.frame.setTitle("Zapenger");
                    StartGUI.frame.getContentPane().removeAll();
                    StartGUI.frame.getContentPane().add (new ChatPage(name));
                    StartGUI.frame.pack();
                    StartGUI.frame.setLocationRelativeTo(null);
                    StartGUI.frame.setVisible (true);
                }
            });
        }
    }
}

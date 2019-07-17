package SocketStuff.Threads;

import SocketStuff.Client;

import javax.swing.*;

public class InputGUI extends Thread
{
    private static volatile boolean flag0=true;
    private String receiver;
    private JTextArea chats;
    private JLabel status;
    public InputGUI(JTextArea chats,String receiver,JLabel status )
    {
        this.receiver=receiver;
        this.chats=chats;
        this.status=status;
    }
    @Override
    public void run()
    {
        String receivedMessage;
        while (flag0)
        {
            receivedMessage = InputStream.read();
            if (receivedMessage.startsWith("##Status-"+receiver))
            {
                String temp = receivedMessage.split("-")[2];
                if (temp.startsWith("Typing"))
                {
                    if (temp.split("##")[1].equals(Client.getUserName()))
                        status.setText("Typing");
                    continue;
                }
                else
                    status.setText(receivedMessage.split("-")[2].split("##")[0]);
                continue;
            }
            chats.setEditable(true);
            String[] temp = receivedMessage.split("##");
            if (temp[0].equals(receiver))
            {
                chats.append(receiver+" : "+temp[1]);
                chats.append("\n");
            }
            if (temp[0].equals(Client.getUserName()))
            {
                chats.append("You : "+temp[1]);
                chats.append("\n");
            }
            chats.setEditable(false);
        }
    }
    public static void end()
    {
        flag0=false;
    }
    public static void con()
    {
        flag0=true;
    }
}

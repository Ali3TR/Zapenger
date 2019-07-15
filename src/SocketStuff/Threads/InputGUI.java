package SocketStuff.Threads;

import SocketStuff.Client;

import javax.swing.*;

public class InputGUI extends Thread
{
    private static volatile boolean flag0=true;
    private String receiver;
    private JTextArea chats;
    private JLabel status;
    private OutputStream outputStream = new OutputStream(Client.getSocket());
    private InputStream inputStream = new InputStream(Client.getSocket());
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
            receivedMessage = inputStream.read();
            if (receivedMessage.startsWith("##"))
            {
                status.setText(receivedMessage.split("##")[1]);
                continue;
            }
            chats.setEditable(true);
            String[] temp = receivedMessage.split("##");
            if (temp[0].equals(receiver))
                chats.append(receiver+" : "+temp[1]);
            if (temp[0].equals(Client.getUserName()))
                chats.append("You : "+temp[1]);
            chats.append("\n");
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

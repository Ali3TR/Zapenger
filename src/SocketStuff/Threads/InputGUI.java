package SocketStuff.Threads;

import SocketStuff.Client;

import javax.swing.*;

public class InputGUI extends Thread
{
    private static volatile boolean flag=true;
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
        System.out.println("thread ran");
        OutputStream.send("##Status-"+receiver);
        receivedMessage = inputStream.read();
        status.setText(receivedMessage.split("##")[1]);
        outputStream.send("##LoadChats-"+ Client.getUserName()+"-"+receiver);
        System.out.println("message sent");
        while (flag)
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
        flag=false;
    }
    public static void con()
    {
        flag=true;
    }
}

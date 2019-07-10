package SocketStuff.Threads;

import SocketStuff.Client;

import javax.swing.*;

public class InputGUI extends Thread
{
    private String receiver;
    private JTextArea chats;
    private OutputStream outputStream = new OutputStream(Client.getSocket());
    private InputStream inputStream = new InputStream(Client.getSocket());
    public InputGUI(JTextArea chats,String receiver)
    {
        this.receiver=receiver;
        this.chats=chats;
    }
    @Override
    public void run()
    {
        String receivedMessage;
        System.out.println("thread ran");
        outputStream.send("##LoadChats-"+ Client.getUserName()+"-"+receiver);
        System.out.println("message sent");
        while (true)
        {
            receivedMessage = inputStream.read();
            chats.setEditable(true);
            System.out.println("recevied");
            String[] temp = receivedMessage.split("##");
            if (temp[0].equals(receiver))
                chats.append(receiver+" : "+temp[1]);
            if (temp[0].equals(Client.getUserName()))
                chats.append("You : "+temp[1]);
            chats.append("\n");
            chats.setEditable(false);
        }
    }
}

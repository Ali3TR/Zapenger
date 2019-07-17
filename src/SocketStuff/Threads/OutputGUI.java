package SocketStuff.Threads;

import SocketStuff.Client;

public class OutputGUI extends Thread
{
    private static volatile boolean flag0=true;
    private static volatile boolean flag1=false;
    private static volatile boolean flag2=false;
    private static volatile boolean flag3=false;
    private String receiver;

    public OutputGUI(String receiver)
    {
        this.receiver=receiver;
    }
    @Override
    public void run()
    {
        OutputStream.send("##GetStatus-"+receiver);
        OutputStream.send("##LoadChats-"+ Client.getUserName()+"-"+receiver);
        while (flag0)
        {
            if (flag2)
            {
                OutputStream.send("##SetStatus-Typing##"+receiver);
                flag2=false;
            }
            if (flag3)
            {
                OutputStream.send("##SetStatus-Online");
                flag3=false;
            }
            if (flag1)
            {
                OutputStream.send("##GetStatus-"+receiver);
                flag1=false;
            }
        }
    }
    public static void sendRequest()
    {
        flag1=true;
    }
    public static void sendTyping()
    {
        flag2=true;
    }
    public static void sendOnline()
    {
        flag3=true;
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

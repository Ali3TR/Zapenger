package SocketStuff.Threads;

import java.util.concurrent.TimeUnit;

public class Status extends Thread
{
    private static volatile boolean flag=true;
    private String username;
    public Status(String username)
    {
        this.username=username;
    }
    @Override
    public void run()
    {
        while (flag)
        {
            try
            {
                TimeUnit.SECONDS.sleep(3);
            }
            catch (InterruptedException error)
            {
                System.err.println(error);
            }
            if (flag)
                OutputStream.send("##Status-"+username);
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

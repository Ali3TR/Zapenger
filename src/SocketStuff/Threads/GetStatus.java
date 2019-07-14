package SocketStuff.Threads;

import java.util.concurrent.TimeUnit;

public class GetStatus extends Thread
{
    private static volatile boolean flag=true;
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
                OutputGUI.sendRequest();
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

package SocketStuff.Threads;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class OutputStream
{
    private static BufferedWriter sentRead;
    private static java.io.OutputStream outputStream;

    public OutputStream(Socket socket)
    {
        try
        {
            outputStream = socket.getOutputStream();
            sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
        }
        catch (IOException error)
        {
            System.err.println(error + ":Error setting up output stream!");
        }
    }

    public static void send(String receivedMessage)
    {
        try
        {
            sentRead.write(receivedMessage);
            sentRead.newLine();
            sentRead.flush();
        }
        catch (IOException error)
        {
            System.err.println(error);
        }
    }

    public static void close()
    {
        try
        {
            sentRead.close();
            outputStream.close();
        }
        catch (IOException error)
        {
            System.err.println(error);
        }

    }
}

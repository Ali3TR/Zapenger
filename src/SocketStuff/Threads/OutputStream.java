package SocketStuff.Threads;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class OutputStream
{
    private Socket socket;
    private static BufferedWriter sentRead;

    public OutputStream(Socket socket)
    {
        this.socket=socket;
        try
        {
            java.io.OutputStream outputStream = socket.getOutputStream();
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
}

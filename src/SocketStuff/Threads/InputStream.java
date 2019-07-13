package SocketStuff.Threads;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputStream extends Thread
{
    private Socket socket;
    public static BufferedReader receiveRead;
    public InputStream(Socket socket)
    {
        this.socket=socket;
        try
        {
            java.io.InputStream inputStream = socket.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(inputStream));
        }
        catch (IOException error)
        {
            System.err.println(error + ":Error setting up input stream!");
        }
    }
    public static String read()
    {
        try
        {
            String receiveMessage;
            while ((receiveMessage = receiveRead.readLine()) != null)
            {
                return receiveMessage;
            }
        }
        catch (IOException error)
        {
            System.out.println(error);
        }

        return "Error";
    }
}

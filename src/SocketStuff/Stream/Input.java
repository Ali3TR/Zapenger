package SocketStuff.Stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input extends Thread
{
    private Socket socket;
    int isAuthorized=-1;
    public Input(Socket socket)
    {
        this.socket=socket;
    }
    @Override
    public void run()
    {
        BufferedReader receiveRead;
        try
        {
            InputStream inputStream = socket.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(inputStream));
            String receiveMessage;
            while(true)
            {
                if((receiveMessage = receiveRead.readLine()) != null) //receive from server
                {
                    if (receiveMessage.startsWith("##"))
                    {

                    }
                    else
                        System.out.println(receiveMessage);
                }
            }
        }
        catch (IOException error)
        {
            System.err.println(error + ":Error setting up input stream!");
        }
    }
    public int getIsAuthorized()
    {
        return isAuthorized;
    }
}

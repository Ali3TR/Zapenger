package SocketStuff.Stream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Output extends Thread
{
    private String userName;
    private Socket socket;
    public Output(Socket socket,String userName)
    {
        this.socket=socket;
        this.userName=userName;
    }
    @Override
    public void run()
    {
        BufferedWriter sentRead;
        try
        {
            OutputStream outputStream = socket.getOutputStream();
            sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
            sentRead.write("##Username-"+userName);
            sentRead.newLine();
            sentRead.flush();
            String sendMessage;
            Scanner keyRead = new Scanner(System.in);
            while(true)
            {
                sendMessage = keyRead.nextLine();  // keyboard reading
                sentRead.write(sendMessage);
                sentRead.newLine();
                sentRead.flush();
            }
        }
        catch (IOException error)
        {
            System.err.println(error + ":Error setting up output stream!");
        }
    }
}

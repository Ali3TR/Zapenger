package One_to_one_chat.Stream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Output extends Thread
{
    private Socket socket;
    public Output(Socket socket)
    {
        this.socket=socket;
    }
    @Override
    public void run()
    {
        Scanner keyRead = new Scanner(System.in);
        BufferedWriter sentRead;
        try
        {
            OutputStream outputStream = socket.getOutputStream();
            sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
            String sendMessage;
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

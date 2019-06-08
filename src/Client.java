import Stream.Input;
import Stream.Output;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    public Client(String userName)
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 37425);
            Output output = new Output(socket,userName);
            Input input = new Input(socket);
            output.start();
            input.start();
            System.out.println("Connected");
        }
        catch (IOException error)
        {
            System.out.println("No server Found!");
        }
    }
}
package One_to_one_chat;

import One_to_one_chat.Stream.Input;
import One_to_one_chat.Stream.Output;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 37425);
            Output output = new Output(socket);
            Input input = new Input(socket);
            input.start();
            output.start();
            System.out.println("Connected");
        }
        catch (IOException error)
        {
            System.err.println(error +"(Error connecting to server!)");
        }
    }
}
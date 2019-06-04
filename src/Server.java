import Stream.Input;
import Stream.Output;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(37425);
            Socket socket = serverSocket.accept( );
            Output output = new Output(socket);
            Input input = new Input(socket);
            input.start();
            output.start();
            System.out.println("Client Connected");
        }
        catch (IOException error)
        {
            System.err.println(error +": error setting up socket");
        }
    }
}                        
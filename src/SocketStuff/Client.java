package SocketStuff;

import SocketStuff.Stream.Input;
import SocketStuff.Stream.Output;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    private static boolean isLogedIn=true;
    public Client(String userName,String pass)
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 37425);
            Output output = new Output(socket,userName+"#"+pass);
            Input input = new Input(socket);
            output.start();
            input.start();
            isLogedIn = input.isLogedIn();
            System.out.println("Connected");
        }
        catch (IOException error)
        {
            System.out.println("No server Found!");
        }
    }
    public static boolean isLogedIn()
    {
        return isLogedIn;
    }
}
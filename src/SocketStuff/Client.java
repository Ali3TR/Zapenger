package SocketStuff;

import GUI.StartGUI;
import SocketStuff.Stream.Input;
import SocketStuff.Stream.Output;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import GUI.WelcomePage;

public class Client
{
    private static String userName;
    private static String pass;
    public Client(String userName)
    {
        StartGUI startGUI = new StartGUI();
        /*try
        {
            Socket socket = new Socket("127.0.0.1", 37425);
            //isAuthorized(socket);
            Output output = new Output(socket,userName);
            Input input = new Input(socket);
            input.start();
            output.start();
        }
        catch (IOException error)
        {
            System.out.println("No server Found!");
        }*/
    }
    public static Socket connect()
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 37425);
            return socket;
        }
        catch (IOException error)
        {
            System.out.println("No server Found!");
        }
        return new Socket();
    }
    public static int isAuthorized(Socket socket)
    {
        try
        {
            BufferedWriter sentRead;
            OutputStream outputStream = socket.getOutputStream();
            sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
            BufferedReader receiveRead;
            InputStream inputStream = socket.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("Streams are set!");
            sentRead.write("##Info-"+userName+"-"+pass);
            sentRead.newLine();
            sentRead.flush();
            System.out.println("Message sent wating for server to respond!");
            String receiveMessage;
            while ((receiveMessage = receiveRead.readLine()) != null)
            {
                System.out.println("server answered!");
                System.out.println(receiveMessage);
                if (receiveMessage.equalsIgnoreCase("##NotLogedIn"))
                {
                    return -1;
                }
                else if (receiveMessage.equalsIgnoreCase("##LogedIn"))
                {
                    return 1;
                }
                else if (receiveMessage.equalsIgnoreCase("##NotFound"))
                {
                    return 0;
                }

            }
            System.out.println("closing stuff");
            sentRead.close();
            outputStream.close();
            receiveRead.close();
            inputStream.close();
            System.out.println("Stuff closed");
        }
        catch (IOException error)
        {
            System.out.println(error);
        }
        return -2;
    }
    public static void setInfo(String userName,String pass)
    {
        Client.userName=userName;
        Client.pass=pass;
    }
}
package SocketStuff;

import GUI.StartGUI;
import SocketStuff.Threads.InputStream;
import SocketStuff.Threads.OutputStream;
import java.io.*;
import java.net.Socket;

public class Client
{
    private static String userName;
    private static String pass;
    private static Socket socket;
    public Client()
    {
        try
        {
            Client.socket = new Socket("127.0.0.1", 37425);
            System.out.println("Socket set");
        }
        catch (IOException error)
        {
            System.out.println("No server Found!");
        }
        OutputStream output= new OutputStream(socket);
        InputStream input=new InputStream(socket);
        System.out.println("starting GUI");
        StartGUI startGUI = new StartGUI();
        /*try
        {
            Socket socket = new Socket("127.0.0.1", 37425);
            //isAuthorized(socket);
            OutputStream output = new OutputStream(socket,userName);
            InputStream input = new InputStream(socket);
            input.start();
            output.start();
        }
        catch (IOException error)
        {
            System.out.println("No server Found!");
        }*/
    }
    public static Socket getSocket()
    {
        return Client.socket;
    }
    public static int isAuthorized()
    {
        OutputStream.send("##Info-"+userName+"-"+pass);
        String receiveMessage = InputStream.read();
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
        else
            return -2;
    }
    public static void setInfo(String userName,String pass)
    {
        Client.userName=userName;
        Client.pass=pass;
    }
    public static String getUserName()
    {
        return Client.userName;
    }
    public static void close()
    {
        try
        {
            socket.close();

        }
        catch (IOException error)
        {
            System.err.println(error);
        }
    }
}
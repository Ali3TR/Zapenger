package SocketStuff;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MegaServer
{
    private static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private static boolean finished = false;
    public static void main(String[] args)
    {
        Thread input = new ServerInput();
        input.start();
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(37425);
        }
        catch (IOException error)
        {
            System.err.println(error +"(Error setting up Server on Desired port!)");
        }
        try
        {
            while (!finished)
            {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
                clientList.add(clientHandler);
            }
        }
        catch (IOException error)
        {
            System.err.println(error + "(Error Accepting SocketStuff.Client "+(clientList.size()+1)+")");
        }
    }
    public static ArrayList<ClientHandler> getClientList()
    {
        return clientList;
    }
    public static void end()
    {
        finished=true;
    }
    public static int getClientNumber(String userName)
    {
        for (int k=0;k<clientList.size();k++)
        {
            if (clientList.get(k).getUserName().equalsIgnoreCase(userName))
                return k;
        }
        return -1;
    }
}

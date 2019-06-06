package Groupchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MegaServer
{
    static ArrayList<ClientHandler> clientList = new ArrayList<>();
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;
        boolean finished = false;
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
                ClientHandler clientHandler = new ClientHandler(socket,clientList.size(),String.format("Client %d",clientList.size()));
                clientHandler.start();
                clientList.add(clientHandler);
            }
        }
        catch (IOException error)
        {
            System.err.println(error + "(Error Accepting Client "+(clientList.size()+1)+")");
        }
    }
    public static ArrayList<ClientHandler> getClientList()
    {
        return clientList;
    }
}

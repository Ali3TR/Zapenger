package Groupchat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread
{
    private Socket socket;
    private int correctClient;
    private String correctClientName;
    public ClientHandler(Socket socket,int correctClient,String correctClientName)
    {
        this.socket=socket;
        this.correctClient=correctClient;
        this.correctClientName=correctClientName;
    }
    @Override
    public void run()
    {
        while (true)
        {
            BufferedReader receiveRead;
            try
            {
                InputStream inputStream = socket.getInputStream();
                receiveRead = new BufferedReader(new InputStreamReader(inputStream));
                String receiveMessage;
                while(true)
                {
                    if((receiveMessage = receiveRead.readLine()) != null) //receive from server
                    {
                        sendToAll(receiveMessage);
                    }
                }
            }
            catch (IOException error)
            {
                System.err.println(error + ":Error setting up input stream!");
            }
        }
    }
    public void sendToAll(String receiveMessage)
    {
        ArrayList<ClientHandler> clientList = MegaServer.getClientList();
        for (int k=0;k<clientList.size();k++)
        {
            if (k==correctClient)
                continue;
            BufferedWriter sentRead;
            try
            {
                OutputStream outputStream = clientList.get(k).getSocket().getOutputStream();
                sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
                sentRead.write(correctClientName + " : "+receiveMessage);
                sentRead.newLine();
                sentRead.flush();
            }
            catch (IOException error)
            {
                System.err.println(error + ":Error setting up output stream!");
            }
        }
    }
    public Socket getSocket()
    {
        return socket;
    }
}

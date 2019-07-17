package SocketStuff;

import DataBase.Account;
import DataBase.AccountDB;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import DataBase.Chats;

public class ClientHandler extends Thread
{
    private volatile boolean flag=true;
    private Socket socket;
    private String userName;
    private InputStream inputStream;
    private BufferedReader receiveRead;
    private OutputStream outputStream;
    private BufferedWriter sentRead;
    public ClientHandler(Socket socket)
    {
        this.socket=socket;
    }
    @Override
    public void run()
    {
            try
            {
                inputStream = socket.getInputStream();
                receiveRead = new BufferedReader(new InputStreamReader(inputStream));
                String receiveMessage;
                while(flag)
                {
                    if((receiveMessage = receiveRead.readLine()) != null)
                    {
                        System.out.println("received message is : " +receiveMessage);
                        if (receiveMessage.startsWith("##"))
                        {
                            command(receiveMessage);
                        }
                        else
                        {
                            if (receiveMessage.length()==0)
                                continue;
                            String[] temp = receiveMessage.split("##");
                            Chats.addToChat(userName+"##"+temp[1],userName,temp[0]);
                        }
                    }
                }
            }
            catch (IOException error)
            {
                System.err.println(error + ":Error setting up input stream!");
            }
    }
    private void send(String receiveMessage,ClientHandler clientHandler)
    {
        try
        {
            outputStream = clientHandler.getSocket().getOutputStream();
            sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
            sentRead.write(/*"&"+userName+":"+*/receiveMessage);
            sentRead.newLine();
            sentRead.flush();
            System.out.println(receiveMessage);
        }
        catch (IOException error)
        {
            System.err.println(error + ":Error setting up output stream for user "+userName+"!");
        }
    }
    public Socket getSocket()
    {
        return socket;
    }
    public String getUserName()
    {
        return userName;
    }
    private void command(String command)
    {
        String[] temp = command.split("-");
        switch (temp[0])
        {
            case "##Info":
                this.userName=temp[1];
                int isAuthorized = AccountDB.isAuthorized(temp[1],temp[2]);
                switch (isAuthorized)
                {
                    case 1:
                        send("##LogedIn",this);
                        userName=temp[1];
                        break;
                    case -1:
                        send("##NotLogedIn",this);
                        break;
                    case 0:
                        send("##NotFound",this);
                        break;
                }
                break;
            case "##AddUser":
                AccountDB.addAccount(new Account(temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7]));
                break;
            case "##UpdateUser":
                AccountDB.updateInfo(new Account(temp[1].trim(),temp[2].trim()
                        ,temp[3].trim(),userName,temp[4].trim(),temp[5].trim(),"Offline"));
                break;
            case "##SetStatus":
                AccountDB.setStatus(userName,temp[1]);
                break;
            case "##GetStatus":
                send("##Status-"+temp[1].split("##")[0]+"-"+AccountDB.getStatus(temp[1].split("##")[0]),this);
                break;
            case "##AddToChat":
                Chats.addToChat(temp[3],temp[2],temp[1]);
                int temp1=MegaServer.getClientNumber(temp[1]);
                if(temp1!=-1)
                    send(temp[3],MegaServer.getClientList().get(temp1));
                break;
            case "##LoadChats":
                ArrayList<String> chats = Chats.loadChat(temp[1],temp[2]);
                send(String.valueOf(chats.size()),this);
                for (int k=0;k<chats.size();k++)
                    send(chats.get(k),this);
                break;
            case "##ChatWith":
                ArrayList<String> listOfChats = Chats.hasChatWith(temp[1]);
                StringBuilder list = new StringBuilder();
                for(int k=0;k<listOfChats.size();k++)
                {
                    list.ensureCapacity(20*k+2);
                    list.append(listOfChats.get(k)+"##");
                }
                send("##ChatWith-"+list.toString(),this);
                break;
            case "##StartChat":
                String temp5 = AccountDB.isUser(temp[1]);
                if (temp5.equals("Found"))
                {
                    Chats.startChat(userName,temp[1]);

                }
                send(temp5,this);
                break;
            case "##Close":
                close();
                break;
            default:
                System.out.println("You can not send a text starting with ##.");
        }
    }
    public void close()
    {
        AccountDB.setStatus(userName,"Offline");
        try
        {
            flag=false;
            receiveRead.close();
            sentRead.close();
            inputStream.close();
            outputStream.close();
            socket.close();
        }
        catch (IOException error)
        {
            System.err.println(error);
        }
    }
}
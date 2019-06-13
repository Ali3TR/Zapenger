package SocketStuff;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread
{
    private Socket socket;
    private String userName;
    private ArrayList<ClientHandler> chatList = new ArrayList<>();
    private ArrayList<ArrayList<String>> groupList = new ArrayList<>();
    int chatNumber=-1;
    int groupNumber=-1;
    public ClientHandler(Socket socket)
    {
        this.socket=socket;
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
                    if((receiveMessage = receiveRead.readLine()) != null)
                    {
                        if (receiveMessage.startsWith("##"))
                        {
                            command(receiveMessage);
                        }
                        else
                        {
                            if (chatNumber!=-1)
                                sendToAll(receiveMessage,chatNumber,1);
                            if (groupNumber!=-1)
                                sendToAll(receiveMessage,groupNumber,2);
                        }
                    }
                }
            }
            catch (IOException error)
            {
                System.err.println(error + ":Error setting up input stream!");
            }
        }
    }
    private void sendToAll(String receiveMessage,int converNumber,int type)
    {
        if (type==1)
        {
            ArrayList<ClientHandler> clientList = new ArrayList<>();
            clientList.add(chatList.get(converNumber));
            sendToAll(receiveMessage,clientList);
        }
        else
        {

        }
    }
    private void sendToAll(String receiveMessage,ArrayList<ClientHandler> clientList)
    {
        for (int k=0;k<clientList.size();k++)
        {
            BufferedWriter sentRead;
            try
            {
                OutputStream outputStream = clientList.get(k).getSocket().getOutputStream();
                sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
                sentRead.write(userName + " : "+receiveMessage);
                sentRead.newLine();
                sentRead.flush();
            }
            catch (IOException error)
            {
                System.err.println(error + ":Error setting up output stream for user "+userName+"!");
            }
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
    private int correctClient(ArrayList<ClientHandler> clientList)
    {
        for (int k=0;k<clientList.size();k++)
        {
            if (clientList.get(k).userName.equalsIgnoreCase(this.userName))
                return k;
        }
        return -1;
    }
    private int hasChat(String userName)
    {
        for (int k=0;k<chatList.size();k++)
        {
            if (chatList.get(k).getUserName().equalsIgnoreCase(userName))
                return k;
        }
        return -1;
    }
    public ArrayList<ClientHandler> getChatList()
    {
        return chatList;
    }
    private boolean isUser(String userNameAndPass)
    {
        String[] temp = new String[2];
        temp = userNameAndPass.split("#");


        return true;
    }
    private void command(String command)
    {
        String[] temp = command.split("-");
        switch (temp[0])
        {
            case "##Chat":
                if (hasChat(temp[1])!=-1)
                {
                    chatNumber=hasChat(temp[1]);
                }
                else
                {
                    chatList.add(MegaServer.getClientList().get(MegaServer.getClientNumber(temp[1])));
                    MegaServer.getClientList().get(MegaServer.getClientNumber(temp[1])).getChatList().add(MegaServer.getClientList().get(MegaServer.getClientNumber(userName)));
                    chatNumber=chatList.size()-1;
                }
                break;
            case "##Group":
                if (temp.length==2)
                {
                    for (int k=0;k<groupList.size();k++)
                        if (groupList.get(k).get(0).equalsIgnoreCase(temp[1]))
                        {
                            groupNumber=k;
                            break;
                        }
                }
                else
                {
                    ArrayList<String> tempArray = new ArrayList<>();
                    tempArray.add(temp[1]);
                    for (int k=2;k<temp.length;k++)
                        tempArray.add(temp[k]);
                    groupList.add(tempArray);
                    groupNumber=groupList.size()-1;
                }

                break;
            default:
                System.out.println("You can not send a text starting with ##.");
        }
    }
}

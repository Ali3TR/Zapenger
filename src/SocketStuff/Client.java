package SocketStuff;

import SocketStuff.Stream.Input;
import SocketStuff.Stream.Output;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    String userName;
    private int isAuthorized=-1;
    public Client(String userName)
    {
        this.userName=userName;
        try
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
        }
    }
    public void isAuthorized(Socket socket)
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            BufferedWriter sentRead;
            OutputStream outputStream = socket.getOutputStream();
            sentRead = new BufferedWriter(new OutputStreamWriter(outputStream));
            BufferedReader receiveRead;
            InputStream inputStream = socket.getInputStream();
            receiveRead = new BufferedReader(new InputStreamReader(inputStream));
            while (isAuthorized!=1)
            {
                String userName = sc.nextLine();
                String pass = sc.nextLine();
                sentRead.write("##"+userName+"-"+pass);
                String receiveMessage = receiveRead.readLine();
                if (receiveMessage.equalsIgnoreCase("##NotLogedIn"))
                {
                    System.out.println("Wrong password(password is case sensitive)!");
                    isAuthorized=-1;
                }
                else if (receiveMessage.equalsIgnoreCase("##LogedIn"))
                {
                    isAuthorized=1;
                    System.out.println("You are logged in!");
                    sentRead.write("##Close");
                }
                else if (receiveMessage.equalsIgnoreCase("##NotFound"))
                {
                    isAuthorized=0;
                    System.out.println("User Not found!try creating one!");
                }
            }
            sc.close();
            sentRead.close();
            outputStream.close();
            receiveRead.close();
            inputStream.close();
        }
        catch (IOException error)
        {
            System.out.println(error);
        }
    }
}
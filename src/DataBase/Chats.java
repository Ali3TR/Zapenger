package DataBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Chats
{
    public static ArrayList<String> loadChats(String sender,String receiver)
    {
        String sr1;
        String sr2;
        if (sender.compareTo(receiver)>0)
        {
            sr1=sender;
            sr2=receiver;
        }
        else
        {
            sr1=receiver;
            sr2=sender;
        }
        ArrayList<String> chats = new  ArrayList<>();
        try
        {
            Scanner input = new Scanner(Paths.get("/home/sajad/Desktop/Sajad/GIT/Zapenger/src/DataBase/Chats/"+sr1+"##"+sr2+".txt"));
            while (input.hasNext())
            {
                chats.add(input.nextLine());
            }
            input.close();
        }
        catch (IOException error)
        {
            System.err.println(error);
        }
        return chats;
    }
    public static void addToChat(String message,String sender,String receiver)
    {
        String sr1;
        String sr2;
        if (sender.compareTo(receiver)>0)
        {
            sr1=sender;
            sr2=receiver;
        }
        else
        {
            sr1=receiver;
            sr2=sender;
        }
        try
        {
            BufferedWriter output = new BufferedWriter(new FileWriter("/home/sajad/Desktop/Sajad/GIT/Zapenger/src/DataBase/Chats/"+sr1+"##"+sr2+".txt",true));
            //BufferedWriter output = Files.newBufferedWriter(Paths.get("/home/sajad/Desktop/Sajad/GIT/Zapenger/src/DataBase/"+sr1+"##"+sr2+".txt"));
            output.append(message);
            output.newLine();
            output.close();
        }
        catch (IOException error)
        {
            System.err.println(error);
        }
    }
    public static ArrayList<String> hasChatWith(String userName)
    {
        ArrayList<String> chats= new ArrayList<>();
        String path = "/home/sajad/Desktop/Sajad/GIT/Zapenger/src/DataBase/Chats/";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (int k=0;k<listOfFiles.length;k++)
        {
            String[] temp = listOfFiles[k].getName().split("##");
            if(temp[0].equals(userName))
            {
                chats.add(temp[1]);
            }
            if ( temp[1].equals(userName))
            {
                chats.add(temp[0]);
            }
        }
        return chats;
    }
}

package SocketStuff;

import DataBase.Account;
import DataBase.AccountDB;
import GUI.ChatPage;
import SocketStuff.Threads.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DBTest
{
    public static void main(String[] args) {
        AccountDB accountDB = new AccountDB();
        //AccountDB.updateInfo(new Account("MohamadSajad","","","Sajad","","",""));
        //System.out.println("hiii- Sajad Mohamad ----".split("-")[1].trim());
        JFrame frame;
        frame = new JFrame("Zapenger");
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                OutputStream.send("##Close");
                InputGUI.end();
                GetStatus.end();
                OutputGUI.end();
                OutputStream.close();
                InputStream.close();
                Client.close();
                e.getWindow().dispose();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        frame.getContentPane().add (new ChatPage("Sajad",true));
        frame.pack();
        frame.setVisible (true);
    }
}

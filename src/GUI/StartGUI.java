package GUI;

import SocketStuff.Client;
import SocketStuff.Threads.InputStream;
import SocketStuff.Threads.OutputStream;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartGUI
{
    public static JFrame frame = new JFrame ("Zapenger");
    public StartGUI()
    {
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                OutputStream.send("##Close");
                OutputStream.close();
                InputStream.close();
                Client.close();
                e.getWindow().dispose();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        frame.getContentPane().add (new WelcomePage());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);

    }
}

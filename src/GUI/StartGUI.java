package GUI;

import javax.swing.*;

public class StartGUI
{
    private static JFrame frame = new JFrame ("Zapenger");
    public StartGUI()
    {
        //Frame stuff
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new WelcomePage());
        frame.pack();
        frame.setVisible (true);
    }
    public static void Hide()
    {
        frame.setVisible(false);
    }
}

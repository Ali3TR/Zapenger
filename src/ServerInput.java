import java.util.Scanner;

public class ServerInput extends Thread
{
    @Override
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("End"))
                MegaServer.end();
        }
    }
}

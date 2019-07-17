package DataBase;

import java.sql.*;

public class AccountDB
{
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public AccountDB()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=zapenger",
                    "postgres",
                    "123456");
        }
        catch (ClassNotFoundException | SQLException error)
        {
            System.err.println(error);
        }
    }

    public static void addAccount(Account account)
    {
        try
        {
            preparedStatement = connection.prepareStatement("insert into account values (default, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getUsername());
            preparedStatement.setString(5, account.getPassword());
            preparedStatement.setString(6, account.getPicturePath());
            preparedStatement.setString(7, account.getStatus());
            preparedStatement.executeUpdate();
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }

    public static String getStatus(String username)
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from account where username = ? ");
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("status");
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
        return "Notfound";
    }

    public static void setStatus(String username,String status)
    {
        String temp;
        if (status.contains("Online"))
            temp="Online";
        else if (status.contains("Offline"))
            temp="Offline";
        else
            temp = "Typing##"+status.split("##")[1];
        try
        {
            preparedStatement = connection.prepareStatement("update account set status = ? where username = ?");
            preparedStatement.setString(1, temp);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }

    public static void updateInfo(Account account)
    {
        if (!account.getFirstName().equals(""))
            updateOneInfo(account.getUsername(),"firstname",account.getFirstName());
        if (!account.getLastName().equals(""))
            updateOneInfo(account.getUsername(),"lastname",account.getLastName());
        if (!account.getEmail().equals(""))
            updateOneInfo(account.getUsername(),"email",account.getEmail());
        if (!account.getPassword().equals(""))
            updateOneInfo(account.getUsername(),"pass",account.getPassword());
        if (!account.getPicturePath().equals(""))
            updateOneInfo(account.getUsername(),"picturepath",account.getPicturePath());

    }

    public static void updateOneInfo(String username, String column, String value)
    {
        try
        {
            preparedStatement = connection.prepareStatement("update account set "+column+" = ? where username = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }

    public static String isUser(String username)
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from account");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                if (resultSet.getString("username").equals(username))
                    return "Found";
            }
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
        return "NotFound";

    }
    public static void setAllUserToOffline()
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from account");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                preparedStatement = connection.prepareStatement("update account set status = 'Offline' where username = ?");
                preparedStatement.setString(1, resultSet.getString("username"));
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException err)
        {
            System.err.println(err);
        }
    }

    public static int isAuthorized(String username, String pass)
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from account where username = ?");
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getString("pass").equals(pass))
            {
                return 1;
            }
        }
        catch (SQLException error)
        {
            return 0;
        }
        return -1;
    }
}

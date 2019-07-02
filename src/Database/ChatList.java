package DataBase;

import java.sql.*;

public class ChatList
{
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    public  ChatList()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection("jdbc:postgresql://localhost:5433/postgres?currentSchema=proj", "postgres", "abcd1234");
        }
        catch (ClassNotFoundException | SQLException error)
        {
            System.err.println(error);
        }
    }
    public void createUserList(String userName)
    {
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("create table "+userName+" (id serial primary key ,userName varchar (20) , massage text[])");
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }
    public void startChat(String sender,String receiver)
    {
        try
        {
            preparedStatement = connection.prepareStatement("insert into sender values (default ,?,?)");
            preparedStatement.setString(1,receiver);
            preparedStatement.setString(2,"Server : Hi");
            preparedStatement.executeUpdate();
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }
    public void sendMessageInChat(String sender,String receiver,String message)
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from "+sender+" where userName = ?");
            preparedStatement.setString(1,receiver);
            preparedStatement.executeQuery(" || "+sender+message);
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("select * from "+receiver+" where userName = ?");
            preparedStatement.setString(1,sender);
            preparedStatement.executeQuery(" || "+sender+message);
            preparedStatement.close();
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }
}

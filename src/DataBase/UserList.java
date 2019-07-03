package DataBase;


import java.sql.*;

public class UserList
{
    /*private Connection connection;
    private PreparedStatement preparedStatement;

    public UserList()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres?currentSchema=zapenger", "postgres", "abcd1234");
        }
        catch (ClassNotFoundException | SQLException error)
        {
            System.err.println(error);
        }
    }

    public void addUser(Account account)
    {
        try
        {
            preparedStatement = connection.prepareStatement("insert into account values (default, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getUsername());
            preparedStatement.setString(5, account.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException error )
        {
            System.err.println(error);
        }
    }

    public void getUserList()
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from userlist");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println(resultSet.getString("username"));
            }
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }

    public int isAuthorized(String username,String pass)
    {
        try
        {
            preparedStatement = connection.prepareStatement("select * from userlist where username = ?");
            preparedStatement.setString(1,username);
        }
        catch (SQLException error)
        {
            System.err.println(error);

        }
        try
        {
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
    }*/

}

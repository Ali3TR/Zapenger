package DataBase;

import java.sql.*;

public class UserList
{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public UserList()
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

    public void addUser(User user)
    {
        try
        {
            preparedStatement = connection.prepareStatement("insert into userlist values (default ,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2,user.getPass());
            preparedStatement.setString(3,user.getEmail());
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
    }

    public String getPerson(String username) throws Exception{
        preparedStatement = connection.prepareStatement("select * from person where username = ?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("username");
    }
    /*
    public void changePass(Person person, String newPass) throws Exception{
        preparedStatement = connection.prepareStatement("update person set pass = ? where username = ?");
        preparedStatement.setString(1, newPass);
        preparedStatement.setString(2,person.getUsername());
        preparedStatement.executeUpdate();
    }

    public void deletePerson(String username) throws Exception{
        preparedStatement = connection.prepareStatement("delete from person where username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
    }
    */
    public void close() throws Exception{
        preparedStatement.close();
        connection.close();
    }
}

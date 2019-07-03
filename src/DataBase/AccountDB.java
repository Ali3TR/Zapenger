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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres?currentSchema=zapenger",
                    "postgres",
                    "abcd1234");
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
            preparedStatement = connection.prepareStatement("insert into account values (default, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getUsername());
            preparedStatement.setString(5, account.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException error)
        {
            System.err.println(error);
        }
    }

    public void getAccounts() throws Exception
    {
        preparedStatement = connection.prepareStatement("select * from account");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            System.out.println(resultSet.getString("username"));
        }
    }

    public String getAccount(String username) throws Exception
    {
        preparedStatement = connection.prepareStatement("select * from account where username = ?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("username");
    }

    public void changePass(Account account, String newPass) throws Exception
    {
        preparedStatement = connection.prepareStatement("update account set pass = ? where username = ?");
        preparedStatement.setString(1, newPass);
        preparedStatement.setString(2, account.getUsername());
        preparedStatement.executeUpdate();
    }

    public void deletePerson(String username) throws Exception
    {
        preparedStatement = connection.prepareStatement("delete from account where username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
    }

    public void close() throws Exception
    {
        preparedStatement.close();
        connection.close();
    }
    public static int isAuthorized(String username,String pass)
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
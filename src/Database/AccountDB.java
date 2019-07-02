package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDB
{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public  AccountDB() throws Exception {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=zapenger", "postgres", "123456");
    }

    public void addAccount(Account account) throws Exception
    {
        preparedStatement = connection.prepareStatement("insert into account values (default, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, account.getFirstName());
        preparedStatement.setString(2, account.getLastName());
        preparedStatement.setString(3, account.getEmail());
        preparedStatement.setString(4, account.getUsername());
        preparedStatement.setString(5, account.getPassword());
        preparedStatement.executeUpdate();
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
}

package DataBase;

public class User
{
    private String username;
    private String pass;
    private String email;
    private int ID;

    public User()
    {

    }

    public User(String username, String pass, String email)
    {
        this.username = username;
        this.pass = pass;
        this.email=email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }
}

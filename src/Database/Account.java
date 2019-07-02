package Database;

public class Account
{
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
//    dataType picture

    public Account()
    {

    }

    public Account(String firstName, String lastName, String email, String username, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setUsername(String userName)
    {
        this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
//    public void setPicture(dataType picture)
//    {
//        this.picture = picture;
//    }

    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
//    public dataType getPicture()
//    {
//        return this.picture;
//    }



}

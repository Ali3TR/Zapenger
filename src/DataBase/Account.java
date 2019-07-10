package DataBase;

public class Account
{
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String picturePath;
//    dataType picture

    public Account()
    {

    }

    public Account(String firstName, String lastName, String email, String username, String password,String picturePath)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.picturePath=picturePath;
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
        this.username = userName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setPicturePath(String picturePath)
    {
        this.picturePath=picturePath;
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
    public String getPicturePath()
    {
        return picturePath;
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
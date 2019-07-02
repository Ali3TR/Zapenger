package SocketStuff;

import DataBase.Account;
import DataBase.User;
import DataBase.UserList;

public class DBTest
{
    public static void main(String[] args) {
        UserList userList = new UserList();
        Account account = new Account("Ali","Sedaghi","Ali@gmail.com","Ali","123456");
        userList.addUser(account);
    }
}

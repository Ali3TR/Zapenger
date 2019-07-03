package SocketStuff;

import DataBase.Account;
import DataBase.AccountDB;

public class DBTest
{
    public static void main(String[] args) {
        AccountDB accountDB = new AccountDB();
        Account account = new Account("Ali","mahdavi","AliM@gmail.com","AliM","123456");
        System.out.println(AccountDB.isAuthorized("Sajad","12345677"));
    }
}

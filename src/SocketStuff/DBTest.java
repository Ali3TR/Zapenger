package SocketStuff;

import DataBase.Account;
import DataBase.AccountDB;

public class DBTest
{
    public static void main(String[] args) {
        AccountDB accountDB = new AccountDB();
        System.out.println(AccountDB.getStatus("Ali"));;
    }
}

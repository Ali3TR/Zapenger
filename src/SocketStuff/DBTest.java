package SocketStuff;

import DataBase.Account;
import DataBase.AccountDB;

public class DBTest
{
    public static void main(String[] args) {
        AccountDB accountDB = new AccountDB();
        AccountDB.updateInfo(new Account("MohamadSajad","","","Sajad","","",""));
        //System.out.println("hiii- Sajad Mohamad ----".split("-")[1].trim());
    }
}

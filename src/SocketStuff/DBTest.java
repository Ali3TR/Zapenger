package SocketStuff;

import DataBase.AccountDB;

public class DBTest
{
    public static void main(String[] args) throws Exception{
        AccountDB accountDB = new AccountDB();
        //Account a1 = new Account("mahdi", "jafari", "mjafari@ymail.com", "mj", "asfgdhdb13478");
        //accountDB.addAccount(a1);
        accountDB.getAccounts();
    }
}

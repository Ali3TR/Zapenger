package Database;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        AccountDB accountDB = new AccountDB();
        Account a1 = new Account("Sajjad", "Naghizade", "ssjd@ymail.com", "sajad", "asdb1378");
        accountDB.addAccount(a1);
        accountDB.getAccounts();
    }
}

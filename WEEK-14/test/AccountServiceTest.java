import model.Account;
import service.AccountService;

public class AccountServiceTest {
    public static void main(String[] args) throws Exception {
        AccountService original = new AccountService();

        original.addAccount(new Account("Om", 5000));
        original.addAccount(new Account("Rahul", 3000));

        original.saveData("test-accounts.dat");

        AccountService restored = new AccountService();
        restored.loadData("test-accounts.dat");

        if (original.getCount() != restored.getCount()) {
            throw new AssertionError("Account count does not match");
        }

        for (int i = 0; i < original.getCount(); i++) {
            Account a = original.getAccount(i);
            Account b = restored.getAccount(i);

            if (!a.getName().equals(b.getName())
                    || a.getBalance() != b.getBalance()) {
                throw new AssertionError("Account data does not match");
            }
        }

        System.out.println("Save and reload test passed!");
    }
}
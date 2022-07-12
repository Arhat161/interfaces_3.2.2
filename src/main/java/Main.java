public class Main {

    public static void main(String[] args) {

        Account savingsAccount = new SavingsAccount("Сберегательный", 10000);
        Account creditAccount = new CreditAccount("Кредитный", 0);
        Account checkingAccount = new CheckingAccount("Рассчетный", 50000);

        // СБЕРЕГАТЕЛЬНЫЙ счет
        accountHeader(savingsAccount);
        savingsAccount.pay(100); // ОШИБКА !!! Нельзя платить со сберегательного счета!
        savingsAccount.addMoney(100); // пополнение сберегательного счета
        savingsAccount.transfer(checkingAccount, 100); // перевод со сберегательного на рассчетный
        savingsAccount.transfer(creditAccount, 5000); // попытка перевести большую сумму на кредитный счет

        // КРЕДИТНЫЙ счет
        accountHeader(creditAccount);
        creditAccount.transfer(checkingAccount, 1000); // списание с кредитного счета
        creditAccount.addMoney(5000); // пополнение кредитного счета
        creditAccount.pay(1250); // оплата с кредитного счета

        // РАСЧЕТНЫЙ счет
        accountHeader(checkingAccount);
        checkingAccount.transfer(savingsAccount, 100); // перевод с РАСЧЕТНОГО на СБЕРЕГАТЕЛЬНЫЙ
        checkingAccount.addMoney(1000); // пополнение РАСЧЕТНОГО
        checkingAccount.pay(500); // оплата с РАСЧЕТНОГО
        checkingAccount.transfer(creditAccount, 10000);
    }

    public static void accountHeader(Account account) {
        System.out.println("\n--- <" + account.getName() + "> , баланс [" + account.getBalance() + "] руб. " +
                "--------------------------------------------");
    }
}

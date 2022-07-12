public class CheckingAccount extends Account implements AccountInterface {

    /*
    Расчетный может выполнять все три операции (платить, переводить, пополнять),
    но не может уходить в минус.
     */

    private String name;
    private int balance;

    public CheckingAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isNull(int amount) {
        return amount > 0 && (this.getBalance() - amount) < 0;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("Оплата суммы [" + amount + "] руб. со счета <" + this.getName() + "> ...");
        boolean res = false;
        if (amount > 0) {
            if (this.isNull(amount)) {
                System.out.println("Недостаточно денег на счету <" + this.getName() + "> для оплаты!");
            } else {
                System.out.println("Оплата выполнена успешно!");
                res = true;
            }
        } else {
            System.out.println("Укажите сумму больше, чем ноль!");
        }
        return res;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        System.out.println("Переводим сумму [" + amount + "] руб. на счет <" + account.getName() + "> ...");
        if (amount > 0) {
            if (this.isNull(amount)) {
                System.out.println("Недостаточно денег на счету <" + this.getName() + "> для перевода на счет <" + account.getName() + "> !");
                return false;
            } else {
                // проверяем что перевод идет не на КРЕДИТНЫЙ счет;
                if (!account.addMoney(amount)) {
                    return false;
                } else {
                    this.balance -= amount; // списание с текущего счета
                    System.out.println("Перевод суммы [" + amount + "] руб. на счет <" + account.getName() + "> выполнен успешно!");
                    return true;
                }
            }
        } else {
            System.out.println("Укажите сумму больше, чем ноль!");
            return false;
        }
    }

    @Override
    public boolean addMoney(int amount) {
        this.balance += amount;
        System.out.println("Пополнение на сумму [" + amount + "] руб. счета <" + this.getName() + ">, баланс : " + this.getBalance() + " руб.");
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }
}


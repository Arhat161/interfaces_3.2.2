public class SavingsAccount extends Account implements AccountInterface {

    /*
    Со сберегательного счета нельзя платить, только переводить и пополнять.
    Также сберегательный не может уходить в минус.
     */

    private int balance;
    private String name;

    public SavingsAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    public boolean isNull(int amount) {
        if (amount > 0 && (this.getBalance() - amount) < 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean pay(int amount) {
        System.out.println("Попытка оплатить [" + amount + "] руб. ... Нельзя производить оплату со счета <" + this.getName() + "> !!!");
        return false;
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

    // пополнение счета
    @Override
    public boolean addMoney(int amount) {
        this.balance += amount;
        System.out.println("Пополнение на сумму [" + amount + "] руб., счета <" + this.getName() + ">, баланс : " + this.getBalance() + " руб.");
        return true;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
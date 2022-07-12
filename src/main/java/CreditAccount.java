public class CreditAccount extends Account implements AccountInterface {

    /*
    Кредитный не может иметь положительный баланс – если платить с него,
    то уходит в минус, чтобы вернуть в 0, надо пополнить его.
     */

    private int balance = 0;
    private String name;

    public CreditAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean pay(int amount) {
        if (amount > 0) {
            this.balance -= amount;
            System.out.println("Оплата суммы [" + amount + "] руб. со счета <" + this.getName() + ">, баланс : " + this.getBalance());
            return true;
        } else {
            System.out.println("Укажите для оплаты со счета <" + this.getName() + "> сумму больше нуля!");
            return false;
        }
    }

    @Override
    public boolean transfer(Account account, int amount) {
        // проверяем что перевод идет не на КРЕДИТНЫЙ счет;
        if (!account.addMoney(amount)) {
            return false;
        } else {
            // на другие счета (Сберегательный, Рассчетный) переводы разрешены
            System.out.println("Переводим сумму [" + amount + "] руб. на счет <" + account.getName() + "> ...");
            this.balance -= amount; // списание с текущего счета
            account.addMoney(amount); // зачисление на другой счет
            System.out.println("Списание [" + amount + "] руб. со счета <" + this.getName() + ">, баланс : " + this.getBalance());
            return true;
        }
    }

    @Override
    public boolean addMoney(int amount) {
        int nowBalance = this.getBalance();
        if ((nowBalance + amount) > 0) {
            System.out.println("Нельзя пополнить счет <" + this.getName() + "> выше нулевого балланса! Текущий баланс [" + nowBalance + "] руб.");
            return false;
        } else {
            this.balance += amount;
            System.out.println("Баланс счета <" + this.getName() + "> успешно пополнен на [" + amount + "] руб.");
            System.out.println("Текущий балланс счета <" + this.getName() + "> : " + this.balance + " руб.");
            return true;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
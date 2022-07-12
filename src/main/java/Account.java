abstract class Account {

    // TODO Ovveride me!
    public abstract boolean pay(int amount);

    public abstract boolean transfer(Account account, int amount);

    public abstract boolean addMoney(int amount);

    public abstract int getBalance();

    public abstract String getName();

}
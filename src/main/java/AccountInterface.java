public interface AccountInterface {

    public boolean pay(int amount);

    public boolean transfer(Account account, int amount);

    public boolean addMoney(int amount);
}
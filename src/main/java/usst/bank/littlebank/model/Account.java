package usst.bank.littlebank.model;


public class Account {

    private String cardId;
    private String cardPassword;
    private double money;


    public Account(){}

    public Account(String cardId,String cardPassword,double money) {
        this.cardId = cardId;
        this.cardPassword = cardPassword;
        this.money = money;
    }

    public void deposit(double depositMoney) {
        this.money += depositMoney;
    }

    public void withdraw(double withdrawMoney) {
        this.money -= withdrawMoney;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}

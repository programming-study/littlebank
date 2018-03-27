package usst.bank.littlebank.DAO;

import usst.bank.littlebank.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    private Account changeResultToAccount(ResultSet resultSet) {
        Account account = new Account();
        try {
            while (resultSet.next()) {
                account.setCardId(resultSet.getString("card_id"));
                account.setCardPassword(resultSet.getString("card_password"));
                account.setMoney(resultSet.getDouble("money"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account searchAccountById(String id) {
        ResultSet resultSet = databaseConnection.searchAccountById(id);
        Account account = changeResultToAccount(resultSet);
        return account;
    }

    public void changeAccountMoney(String id, double money) {
        databaseConnection.changeAccountMoney(id,money);
    }

    public void inertNewAccount(Account account) {
        databaseConnection.insertNewAccount(account.getCardId(), account.getCardPassword(), account.getMoney());
    }
}

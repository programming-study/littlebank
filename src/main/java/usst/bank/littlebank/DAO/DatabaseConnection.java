package usst.bank.littlebank.DAO;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?useSSL=false", "root", "mysql");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchAccountById(String id) {
        try {
            preparedStatement=connection.prepareStatement("SELECT * FROM account WHERE card_id=?");
            preparedStatement.setString(1,id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeAccountMoney(String id, double money) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE account SET money=? WHERE card_id=?");
            preparedStatement.setDouble(1, money);
            preparedStatement.setString(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewAccount(String id, String password, double money) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO account VALUES (?,?,?)");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setDouble(3, money);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package org.it_academy.db_study.Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionLogic {
    public static void deposit(Connection connection, int sum, int accountID) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE accounts SET balance=balance + ? WHERE accountID=?");
        preparedStatement.setInt(1, sum);
        preparedStatement.setInt(2, accountID);
        saveTransaction(connection, sum, accountID);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void withdraw(Connection connection, int sum, int accountID) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE accounts SET balance=balance - ? WHERE accountID=?");
        preparedStatement.setInt(1, sum);
        preparedStatement.setInt(2, accountID);
        saveTransaction(connection, sum, accountID);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private static void saveTransaction(Connection connection, int accountID, int sum) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO Transactions (accountID, amount) VALUES (?, ?)");
        preparedStatement.setInt(1, accountID);
        preparedStatement.setInt(2, sum);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
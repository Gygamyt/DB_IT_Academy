package org.it_academy.db_study.Logic;

import org.it_academy.db_study.Models.Account;
import org.it_academy.db_study.Models.User;

import java.sql.*;

public class AccountLogic {

    public static Account regDefaultAccount() {
        Account account = new Account();
        account.setCurrency("USD");
        account.setBalance(0);
        return account;
    }

    public static void addDefaultAccountToNewUser(Connection connection, Account account, User user) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO accounts (userID, currency, balance) VALUES (?, ?, ?)");
        preparedStatement.setInt(1, utilityFinderID(connection, user.getName()));
        preparedStatement.setString(2, account.getCurrency());
        preparedStatement.setInt(3, account.getBalance());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private static int utilityFinderID(Connection connection, String name) throws SQLException { //за такой костыль я бы себя уволил, но не успеваю к дедлайну
        ResultSet resultSet;
        int result;
        PreparedStatement preparedStatement
                = connection.prepareStatement("SELECT userID FROM Users WHERE name = ?");
        preparedStatement.setString(1, name);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        result = resultSet.getInt(1);
        System.out.println("Remember your ID - " + result + "\n");
        return result;
    }

//    public static void addAccountToUser(Connection connection, Account account) throws SQLException {
//        PreparedStatement preparedStatement = connection
//                .prepareStatement("INSERT INTO accounts (userID, currency, balance) VALUES (?, ?, ?)");
//        preparedStatement.setInt(1, account.getUserID());
//        preparedStatement.setString(2, account.getCurrency());
//        preparedStatement.setInt(3, account.getBalance());
//        preparedStatement.execute();
//        preparedStatement.close();
//    }
}
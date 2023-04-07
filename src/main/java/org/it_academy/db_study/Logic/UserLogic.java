package org.it_academy.db_study.Logic;

import org.it_academy.db_study.Models.User;

import java.sql.*;
import java.util.Scanner;

public class UserLogic {

    public static User regUser() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Enter unique name");
        user.setName(scanner.nextLine());
        System.out.println("Optionally enter address");
        user.setAddress(scanner.nextLine());
        return user;
    }

    public static void addUser(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO users (name, address) VALUES (?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getAddress());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void addUserAndAccount(Connection connection) throws SQLException {
        User user = regUser();
        addUser(connection, user);
        AccountLogic.addDefaultAccountToNewUser(connection, AccountLogic.regDefaultAccount(), user);
    }
}
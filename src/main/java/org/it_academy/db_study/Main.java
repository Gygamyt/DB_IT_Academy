package org.it_academy.db_study;

import org.it_academy.db_study.Logic.TransactionLogic;
import org.it_academy.db_study.Logic.UserLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        if (DriverStarter.isDriverExist()) {
            Connection connection = DriverManager.getConnection(DriverStarter.getDbUrl());
            int action;
            do {
                printMenu();
                action = new Scanner(System.in).nextInt();
                switch (action) {
                    case 1 -> UserLogic.addUserAndAccount(connection);
                    case 2 -> {
                        System.out.println("Enter sum");
                        int sum = new Scanner(System.in).nextInt();
                        System.out.println("Enter accountID");
                        int accountID = new Scanner(System.in).nextInt();
                        TransactionLogic.deposit(connection, sum, accountID);
                    }
                    case 3 -> {
                        System.out.println("Enter sum");
                        int sum = new Scanner(System.in).nextInt();
                        System.out.println("Enter accountID");
                        int accountID = new Scanner(System.in).nextInt();
                        TransactionLogic.withdraw(connection, sum, accountID);
                    }
                    case 4 -> printMenu();
                }
            } while (action!=4);
        }
    }

    private static void printMenu() {
        System.out.println("1 - create new user\n2 - deposit\n3 - withdraw\n4 - exit");
    }
}
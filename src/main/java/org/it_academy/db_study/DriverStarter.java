package org.it_academy.db_study;

public class DriverStarter {
    private static final String DB_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL =
            "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\main\\resources\\studyDB.db";

    public static String getDbUrl() {
        return DB_URL;
    }

    public static boolean isDriverExist() {
        try {
            Class.forName(DB_DRIVER);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Drivera nety");
            return false;
        }
    }
}
package com.example.javafxmultibanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static Connection conn = null;
    private static final String connStr = "jdbc:sqlite:database.db";

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("O driver JDBC não foi encontrado!");
            e.printStackTrace();
            throw e;
        }
        System.out.println("Driver JDBC registado!");
        try {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            System.out.println("A ligação falhou! Verifique o terminal de saída: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static Connection getConnetion() {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(connStr);
        }catch (Exception e){
            System.out.println("Ocorreu um erro: " + e);
            return null;
        }
    }
}

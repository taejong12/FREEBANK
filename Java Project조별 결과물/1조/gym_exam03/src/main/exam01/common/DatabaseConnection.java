package main.exam01.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USER = "gym";
    private static final String PASSWORD = "1234";

    private static Connection connection = null;

    // DB 연결을 가져오는 메서드
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("📡 DB 연결 성공!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ DB 연결 실패!");
        }
        return connection;
    }

    // DB 연결을 닫는 메서드
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 DB 연결 닫힘!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

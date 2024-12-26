

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
    private static Singleton instance;
    private Connection connection;

    private Singleton() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking_db", "root", "merve");

            System.out.println("Veritabanı bağlantısı başarılı!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}


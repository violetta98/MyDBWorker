package WorkingWithDatabase;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Violetta on 2017-08-26.
 */

// мой вспомагательный класс для работы с Databases
public class DBWorker {

    private static String url;
    private static String username;
    private static String password;
    private static Connection connection;

    public static boolean setConnection() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/violetta.properties")) {
            properties.load(fileInputStream);
            url = properties.getProperty("db.url"); // получаем url из properties файла
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            Driver driver = new FabricMySQLDriver(); // создаем драйвер
            DriverManager.registerDriver(driver); // регистрируем драйвер
            connection = DriverManager.getConnection(url, username, password); // получаем Connection с помощью DriverManager
            return connectionIsSuccessfull();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int executeUpdate(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean connectionIsSuccessfull() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void сloseConnection() {
        try {
            if (connection != null)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

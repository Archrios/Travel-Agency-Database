package database;

import model.Customer;

import java.sql.*;

public class DatabaseConnectionHandler {
    private static final String MYSQL_URL = "jdbc:mysql://localhost/traveldb";
    private static final String USERNAME = "traveldb";
    private static final String PASSWORD = "123456";

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            connection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to MySQL!");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Email FROM Customer_Login WHERE Email = ? && Password = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (!rs.first()) {
                return false;
            }

            //Customer c = new Customer();

            rs.close();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}

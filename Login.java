import java.sql.*;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "raimbek99");

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
            if (result.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a new username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter a new password: ");
        String newPassword = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "raimbek99");

            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users (username, password) VALUES ('" + newUsername + "', '" + newPassword + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

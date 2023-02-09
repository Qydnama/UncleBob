import java.sql.*;

public class Register {
    private String username;
    private String password;

    public Register(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean register() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "raimbek99");

            PreparedStatement checkUsernameStatement = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
            checkUsernameStatement.setString(1, username);
            ResultSet resultSet = checkUsernameStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to register new user.");
        }
        return true;
    }
}

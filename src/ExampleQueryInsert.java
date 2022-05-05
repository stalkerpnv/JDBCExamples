import java.sql.*;
import java.util.Scanner;

public class ExampleQueryInsert {
    static Connection connection;
    static Statement statement;
    static String url = "jdbc:sqlite:user.db";

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url);
    }

    public static void disconnect() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    public static void createTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS peoples( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age INTEGER)";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public static void insertValueIntoTable(String name, String lastname, int age) throws SQLException {
        String query = "INSERT INTO peoples (name, lastname, age) " +
                       "VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, lastname);
        preparedStatement.setInt(3,age);
        preparedStatement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        connect();
        createTable();
        System.out.println("Таблица создана");
        System.out.println("count");
        int count = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < count; i++) {
            System.out.println("name");
            String name = sc.nextLine();
            String lastname = sc.nextLine();
            int age = sc.nextInt();
            sc.nextLine();
            insertValueIntoTable(name,lastname,age);
        }
        disconnect();
    }
}

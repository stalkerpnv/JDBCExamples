import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Соединение с БД
 * @author Petrov Nikolay
 * @version dated May 4, 2022
 */
public class ExampleOne {
    public static void main(String[] args) {
        try{
            // 1. Загружаем JBDC драйвер
            Class.forName("org.sqlite.JDBC");
            // 2. Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection("jdbc:sqlite:user.db");
            System.out.println("Connected");

            connection.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

import java.sql.Connection;
import java.sql.*;
/**
 * Примеры с Select
 * @author Petrov Nikolay
 * @version dated May 4, 2022
 */
public class ExampleQuerySelect {
    public static void main(String[] args) {
        try {
            // 1. Загружаем JBDC драйвер
            Class.forName("org.sqlite.JDBC");
            // 2. Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection("jdbc:sqlite:user.db");
            System.out.println("Connected");
            // 3. Создание объекта класса Statement (Утверждение)
            // предоставляет методы для выполнения запросов
            Statement statement = connection.createStatement();
            // Формирование запроса
            String query = "SELECT * FROM users;";
            // Выполнение запроса и присвоение результата ResultSet
            //Данные, полученные в результате SQL – запроса возвращаются
            // в виде множества результатов, которые хранятся в сущности под названием Result Set.
            //Интерфейс java.sql.ResultSet представляет собой множество результатов, запроса в БД.
            //Экземпляр ResultSet имеет указатель, который указывает на текущую строку
            // в полученном множестве.
            ResultSet rs = statement.executeQuery(query);
            // rs - работает по принципу указателя
            while (rs.next()) {
                // Значение столбца в текущей строке
                // можно получить по индексу
                // String nick = rs.getString(4);
                // либо по имени столбца
                System.out.println("id:" + rs.getInt("id") + " " +
                                   "login:" + rs.getString("login") + " " +
                                   "pass:" + rs.getString("password") + " " +
                                   "nick:" + rs.getString("nick"));
            }
            rs.close();
            // Поиск по определенному логину и паролю

            String loginSearch = "login2";
            String passwordSearch = "pass2";
            // PreparedStatement позволяет подготовить запрос и отформатировать его должным образом.
            String searchquery = "SELECT *from users where login= ? and password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(searchquery);
            preparedStatement.setString(1,loginSearch);
            preparedStatement.setString(2,passwordSearch);

//            Для выполнения запроса PreparedStatement имеет три метода:
//            boolean execute(): выполняет любую SQL-команду
//            ResultSet executeQuery(): выполняет команду SELECT, которая возвращает данные в виде ResultSet
//            int executeUpdate(): выполняет такие SQL-команды, как INSERT, UPDATE, DELETE, CREATE и возвращает количество измененных строк
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("Результат поиска строки");
            while(result.next()){
                System.out.println("id:" + result.getInt("id") + " " +
                        "login:" + result.getString("login") + " " +
                        "pass:" + result.getString("password") + " " +
                        "nick:" + result.getString("nick"));
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

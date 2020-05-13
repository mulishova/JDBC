package study.JDBC;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost/[name]";

        Connection connection = null;

        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("url", "marmot", "12345678");
            Statement select = connection.createStatement(); // для запросов

            ResultSet result = select.executeQuery("select * from [название таблицы]"); // хранит результат запроса, если update/insert - executeUpdate() вызвать

            while (result.next())
            {
                int key = result.getInt([индекс столбца с единицы]);
                String value = result.getString([индекс столбца с единицы]);
                System.out.println("key = " + key);
                System.out.println("value = " + value);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // закрываем соединение с бд
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

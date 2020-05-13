package study.JDBC;

import java.sql.*;

public class ClassDB {
    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public ClassDB(String db) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/" + db;

        this.connection = DriverManager.getConnection("url", "marmot", "12345678");

        this.statement = connection.createStatement();
    }

    int numberLines (String request) throws SQLException { // количество строк таблицы
        int number = 0;

        result = statement.executeQuery(request);

        while (result.next()) {
            number++;
        }

        return number;
    }

    int numberColumns (String table) throws SQLException { // количество столбцов таблицы
        int number = 0;
        String request = "SELECT * FROM " + table;

        result = statement.executeQuery(request);

        ResultSetMetaData rsMetaData = result.getMetaData(); // служебная информация таблицы
        number = rsMetaData.getColumnCount();

        return number;
    }

    int numberColumnsForSelect (String request) throws SQLException { // количество столбцов таблицы
        int number = 0;
        result = statement.executeQuery(request);

        ResultSetMetaData rsMetaData = result.getMetaData(); // служебная информация таблицы
        number = rsMetaData.getColumnCount();

        return number;
    }

    String maxValueInColumn (String table, String column) throws SQLException { // максимальное значение в столбце
        String request = "SELECT max (" + column + ") FROM " + table;

        result = statement.executeQuery(request);

        try {
            if (result.next())
                return result.getString(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    String minValueInColumn (String table, String column) throws SQLException { // минимальное значение в столбце
        String request = "SELECT min (" + column + ") FROM " + table;

        result = statement.executeQuery(request);

        try {
            if (result.next())
                return result.getString(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    String select (String request) throws SQLException {
        this.result = this.statement.executeQuery(request);

        int numberOfColumns = this.numberColumnsForSelect(request);

        ResultSetMetaData rsMetaData = this.result.getMetaData();

        String [][] Data = new String [numberLines(request) + 1][numberColumnsForSelect(request)];
        
        int countColumn = 1;
        int j = 0;

        while (numberColumnsForSelect(request) >= countColumn) {
            if (result.next())
                Data[0][j] = result.getString(j + 1);
            System.out.println(Data[0][j]);
            j++;
        }
        return "";
    }

    String query (String request) throws SQLException { // успешно ли меняются строки
        if (statement.executeUpdate(request) > 0)
            return "OK";
        else
            return "ERROR";
    }
}

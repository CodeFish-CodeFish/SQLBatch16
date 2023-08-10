package jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcIntro {

    @Test
    public void testConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student",
                "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");

//        resultSet.next();
//        System.out.println(resultSet.getString(1) + " " +resultSet.getString(2) + " ");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }


    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student",
                "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();


//        metaData.getColumnCount();

        while (resultSet.next()) {
            System.out.print("| ");
            for (int i = 1; i <= metaData.getColumnCount(); i++){
                System.out.print(resultSet.getString(i) + " |");
            }
            System.out.println();

        }

    }


}

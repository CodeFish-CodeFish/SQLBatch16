package jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcAdv {
    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student",
                "codefish385"
        );
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> resultSetData = new ArrayList<>();



        while (resultSet.next()) {// ITERATING ROW
            Map<String,Object>rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {

                rowMap.put(metaData.getColumnName(i) , resultSet.getObject(i));

            }

            resultSetData.add(rowMap);

        }
//        System.out.println(resultSetData.get(4).get("FIRST_NAME"));

        for ( Map<String , Object>eachMap : resultSetData){
            String firstName = eachMap.get("FIRST_NAME").toString();
            if(firstName.equals("TJ")){
                int salary = Integer.parseInt(eachMap.get("SALARY").toString());
                Assert.assertEquals(2100,salary);
            }

        }



    }





    // VALIDATE  TJ HAS SALARY 2100
    // - 5 MIN
}

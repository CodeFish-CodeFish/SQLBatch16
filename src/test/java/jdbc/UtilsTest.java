package jdbc;

import org.junit.AfterClass;
import org.junit.Test;
import utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsTest {

    @Test
    public void testUtils() throws SQLException {
        ResultSet resultSet = JdbcUtil.queryDB("select * from employees");
//        resultSet.next();
//        System.out.println(resultSet.getString(2));




        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String, Object>> javaResultSet = new ArrayList<>();
        Map<String , Object> rowMap = new HashMap<>();

        while (resultSet.next()){

            for (int i = 1; i<= resultSetMetaData.getColumnCount();i++ ){
                rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
            javaResultSet.add(rowMap);
        }

        System.out.println(javaResultSet.get(0).get("FIRST_NAME"));
    }


    @AfterClass
    public static void closeTest(){
        JdbcUtil.closeConnection();
    }
}

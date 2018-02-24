package cn.fanyetu.durid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zhanghaonan
 * @date 2018/2/12
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Demo1.class.getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select 1 from dual");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(0));
        }

        resultSet.close();
        statement.close();

    }
}

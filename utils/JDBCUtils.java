package com.dltour.manHanRestaurant.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static Connection connection;
    private static DataSource ds;
    //用一个静态代码防止二次编译
    static {
        Properties properties=new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

        try {
            if (is!=null) {
                properties.load(is);
                ds = DruidDataSourceFactory.createDataSource(properties);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //返回连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    //返回datasource
    public static DataSource getDataSource(){
        return ds;
    }

    //关闭连接
    public static void close(PreparedStatement ps, ResultSet rs,Connection connection){
            try {
                if (ps!=null) {
                    ps.close();
                }
                if (rs!=null){
                    rs.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}

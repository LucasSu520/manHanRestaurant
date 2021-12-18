package com.dltour.manHanRestaurant.daos;

import com.dltour.jdbc.jdbcutils.JDBCDruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDao {
    //连接到数据库使用druid
    private QueryRunner qr=new QueryRunner();
    //DML语法
    public int update(String sql,Object... parameters){
        Connection connection=null;
        int update = 0;
        try {
            connection= JDBCDruidUtils.getConnection();
            update=qr.update(connection,sql,parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCDruidUtils.close(null,null,connection);
            return update;
        }

    }

    //查询返回多个对象
    public <T> List<T> queryMulti(String sql, Class<T> clazz, Object... parameters){
        Connection connection=null;
        List<T> list=null;
        try {
            connection=JDBCDruidUtils.getConnection();
            list= qr.query(connection,sql,new BeanListHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCDruidUtils.close(null,null,connection);
            return list;
        }

    }

    //查询返回一个对象
    public <T> Object querySingle(String sql,Class<T> clazz,Object... parameters) {
        Connection connection=null;
        Object object=null;
        try {
            connection = JDBCDruidUtils.getConnection();
            object=qr.query(connection, sql,new BeanHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCDruidUtils.close(null,null,connection);
            return object;
        }
    }

    //查询返回一个值
    public <T> Object queryScalar(String sql,Object... parameters){
        Object scalar=null;
        Connection connection=null;
        try {
            connection=JDBCDruidUtils.getConnection();
            scalar=qr.query(connection,sql,new ScalarHandler<>(),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCDruidUtils.close(null,null,connection);
            return scalar;
        }
    }
}

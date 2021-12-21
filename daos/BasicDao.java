package com.dltour.manHanRestaurant.daos;

import java.util.List;

public interface BasicDao {

    /**
     * 增删改数据库数据
     * @param sql  增删改语句
     * @param parameters 预处理中的参数
     * @return 返回修改影响的行数；
     */
    public int update(String sql,Object... parameters);

    /**
     * 查询多个对象
     * @param sql 查询的sql语句
     * @param clazz 返回的集合的物体的类
     * @param parameters sql中预处理的参数
     * @param <T> 泛型
     * @return 返回包含有T泛型的集合
     */
    public <T> List<T> queryMulti(String sql, Class<T> clazz, Object... parameters);

    /**
     * 查询单行语句
     * @param sql 查询的sql语句
     * @param clazz 查询到物体的类
     * @param parameters 查询的sql的语句参数
     * @param <T> 查询到泛型
     * @return 返回的泛型
     */
    public <T> Object querySingle(String sql,Class<T> clazz,Object... parameters);

    /**
     * 查询单值
     * @param sql 查询的sql语句
     * @param parameters 查询的sql语句中的预处理的参数
     * @param <T> 查询的类的泛型
     * @return 查询的值
     */
    public <T> Object queryScalar(String sql,Object... parameters);

}

package com.dltour.manHanRestaurant.daos;

import com.dltour.manHanRestaurant.domains.Customer;

public interface CustomerDao {
    /**
     * 创建顾客
     * @param name 顾客姓名
     * @param phoneNum 顾客电话
     * @return 返回是否创建成功；
     */
    int createCustomer(String name,String phoneNum);

    /**
     * 根据顾客电话得到顾客信息
     * @param phoneNum 顾客电话
     */
    Customer getCustomer(String phoneNum);

}
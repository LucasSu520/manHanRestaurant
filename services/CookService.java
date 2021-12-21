package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daoImpl.CookDaoImpl;

public class CookService {
    CookDaoImpl cdi;

    //判断输入的厨师ID是否存在
    public boolean isExist(int id){
        cdi=new CookDaoImpl();
        return  cdi.getCook(id)!=null;
    }

    public String getCookName(int id){
        cdi=new CookDaoImpl();
        return cdi.getCookName(id);
    }
}

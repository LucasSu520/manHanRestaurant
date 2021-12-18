package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daos.CookDao;
import com.dltour.manHanRestaurant.domains.Cook;

public class CookService {
    CookDao cd=new CookDao();

    //判断输入的厨师ID是否存在
    public boolean isExist(int id){
        Cook cook=(Cook) cd.querySingle("select * from cook where id=?",Cook.class,id);
        return cook!=null;
    }
}

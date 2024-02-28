package com.okccc.test;

import com.okccc.dao.BaseDao;
import com.okccc.pojo.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/7 16:39:10
 * @Desc:
 */
public class TestBaseDao {
    private static BaseDao baseDao;

    @BeforeAll
    public static void init() {
        baseDao = new BaseDao();
    }

    @Test
    public void testBaseQueryObject(){
        String sql ="select count(1) from user";
        Long count = baseDao.baseQueryObject(sql);
        System.out.println(count);
    }

    @Test
    public void testBaseQuery(){
        String sql ="select * from user";
        List<User> list = baseDao.baseQuery(User.class, sql, true);
        list.forEach(System.out::println);
    }

    @Test
    public void testBaseUpdate(){
        String sql ="insert into user values(DEFAULT,?,?)";
        int rows = baseDao.baseUpdate(sql, "grubby", "123456");
        System.out.println(rows);
    }

}

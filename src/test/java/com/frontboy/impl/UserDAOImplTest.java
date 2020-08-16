package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.UserDAO;
import com.frontboy.model.TUser;
import com.frontboy.model.VUserRole;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDAOImplTest extends UserDAOImpl {
    iHibBaseDAO baseDAO = null;
    UserDAO userDAO = null;
    @Before
    public void init(){
        baseDAO = new HibBaseDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Test
    public void  testCreate() {
        TUser user = new TUser();
        user.setUsername("xiaohong");
        user.setPassword("000000");
        System.out.println(userDAO.create(user));
    }

    @Test
    public void testDelete() {
        System.out.println(userDAO.delete("zhang"));
    }

    @Test
    public void testUpdate() {
        TUser user = (TUser) userDAO.findById("zhang");
        user.setPassword("000000");
        System.out.println(userDAO.update(user));
    }

    @Test
    public void testEditUser() {
        TUser user = (TUser) userDAO.findById("admin");
        user.setPhone("18888888888");
        user.setGender("女");
        System.out.println(userDAO.update(user));
    }

    @Test
    public void testUpdatePass() {
        System.out.println(userDAO.updatePass("admin","123456"));
    }

    @Test
    public void testLogin() {
        VUserRole user = userDAO.login("admin", "000000");
        System.out.println(user.getUsername());
    }

    @Test
    public void testFindById() {
        TUser user = userDAO.findById("zhang");
        System.out.println(user.getPassword());
    }

    @Test
    public void testFindAllUser(){
        List<TUser> tvlist = userDAO.findAllUser("",1,5);
        for ( TUser user: tvlist
        ) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void testUpdateToken() {
    }

    @Test
    public void testGetToken() {
    }
}
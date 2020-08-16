package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.RoleDAO;
import com.frontboy.model.TRole;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RoleDAOImplTest extends UserDAOImpl {
    iHibBaseDAO baseDAO = null;
    RoleDAO roleDAO = null;
    @Before
    public void init(){
        baseDAO = new HibBaseDAOImpl();
        roleDAO = new RoleDAOImpl();
    }

    @Test
    public void testFindById() {
        TRole role = roleDAO.findById(1);
        System.out.println(role.getDescription());
    }

    @Test
    public void testFindByName() {
        TRole role = roleDAO.findByName("guide");
        if (role != null){
            System.out.println("角色名称已存在");
        }else {

            System.out.println("角色名称不存在");
        }
    }

    @Test
    public void findAllRoles() {
        List<TRole> roles = roleDAO.findAllRoles("",1,5);
        for ( TRole role: roles
        ) {
            System.out.println(role.getDescription());
        }
    }

    @Test
    public void testFindAmount() {
        System.out.println(roleDAO.findAmount(""));
    }

    @Test
    public void testCreate() {
        TRole roles = new TRole();
        String role = roleDAO.findById(1).getName();
        roles.setName("test");
        roles.setDescription("测试");
        System.out.println(roleDAO.create(roles));
    }

    @Test
    public void testDelete() {
        System.out.println(roleDAO.delete(4));
    }

    @Test
    public void testUpdate() {
        TRole role = roleDAO.findById(5);
        role.setDescription("测试人员");
        System.out.println(roleDAO.update(role));
    }
}
package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.AgencyDAO;
import com.frontboy.model.TTravelagency;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AgencyDAOImplTest extends UserDAOImpl {

    iHibBaseDAO baseDAO = null;
    AgencyDAO agencyDAO = null;
    @Before
    public void init(){
        baseDAO = new HibBaseDAOImpl();
        agencyDAO = new AgencyDAOImpl();
    }

    @Test
    public void testFindById() {
        TTravelagency agency = agencyDAO.findById(1);
        System.out.println(agency.getName());
    }

    @Test
    public void findByName() {
        TTravelagency agency = agencyDAO.findByName("石林风光旅行社");
        System.out.println(agency);
    }

    @Test
    public void findAllAgency() {
        List<TTravelagency> agency = agencyDAO.findAllAgency("", 1, 10);
        for (TTravelagency list: agency){
            System.out.println(list.getName() + " " + list.getAddress() + " " + list.getIntroduction() + " "
                    + list.getPerson() + " " + list.getStatus() + " " + list.getTel());
        }
    }

    @Test
    public void testFindAmount() {
        System.out.println(agencyDAO.findAmount(""));
    }

    @Test
    public void testCreate() {
        TTravelagency agency = new TTravelagency();
        agency.setName("石林风光旅行社11");
        agency.setIntroduction("石林最好的一家旅行社");
        agency.setPerson("张光泽");
        agency.setTel("155126877634");
        agency.setAddress("石林景区");
        agency.setStatus("启用");
        System.out.println(agencyDAO.create(agency));
    }

    @Test
    public void testDelete() {
        System.out.println(agencyDAO.delete(2));
    }

    @Test
    public void testUpdate() {
        TTravelagency agency = agencyDAO.findById(1);
        agency.setAddress("石林");
        agency.setTel("15424587245");
        agency.setPerson("刘四");
        agency.setIntroduction("天下第一家");
        agency.setName("石林风情");
        agency.setStatus("启用");
        System.out.println(agencyDAO.update(agency));
    }
}
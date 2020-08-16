package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.RedBlockDAO;
import com.frontboy.model.TRedBlock;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RedBlockDAOImplTest extends UserDAOImpl {

    iHibBaseDAO baseDAO = null;
    RedBlockDAO redBlockDAO = null;
    @Before
    public void init(){
        baseDAO = new HibBaseDAOImpl();
        redBlockDAO = new RedBlockDAOImpl();
    }

    @Test
    public void testFindById() {
        TRedBlock redBlock = redBlockDAO.findById(1);
        System.out.println(redBlock.getReason());
    }

    @Test
    public void findAllList() {
        List<TRedBlock> list = redBlockDAO.findAllList("", 1, 10);
        for (TRedBlock redBlock: list){
            System.out.println(redBlock.getId() + " " + redBlock.getType() + " " + redBlock.getTime() + " " + redBlock.getReason() + " " + redBlock.getPerson());
        }
    }

    @Test
    public void findTimeList() {
        List<TRedBlock> list = redBlockDAO.findTimeList("20200101");
        for (TRedBlock redBlock: list){
            System.out.println(redBlock.getId() + " " + redBlock.getType() + " " + redBlock.getTime() + " " + redBlock.getReason() + " " + redBlock.getPerson());
        }
    }

    @Test
    public void testFindAmount() {
        System.out.println(redBlockDAO.findAmount(""));
    }

    @Test
    public void testCreate() {
        TRedBlock tRedBlock = new TRedBlock();
        tRedBlock.setType("黑榜");
        tRedBlock.setPerson("张三");
        tRedBlock.setTime("2020-3-12");
        tRedBlock.setReason("。。。。。。。。。。");
        System.out.println(redBlockDAO.create(tRedBlock));
    }

    @Test
    public void testDelete() {
        System.out.println(redBlockDAO.delete(2));
    }

    @Test
    public void testUpdate() {
        TRedBlock tRedBlock = redBlockDAO.findById(3);
        System.out.println(tRedBlock.getReason() + " " + tRedBlock.getPerson() + " " + tRedBlock.getTime() + " " + tRedBlock.getType());
        tRedBlock.setType("红榜");
        tRedBlock.setTime("2020-1-4");
        tRedBlock.setPerson("李四");
        tRedBlock.setReason("111111");
        System.out.println(redBlockDAO.update(tRedBlock));
        System.out.println(tRedBlock.getReason() + " " + tRedBlock.getPerson() + " " + tRedBlock.getTime() + " " + tRedBlock.getType());
    }
}
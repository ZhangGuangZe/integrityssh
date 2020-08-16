package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.BlockDAO;
import com.frontboy.model.TBlock;
import com.frontboy.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class BlockDAOImplTest extends UserDAOImpl {

    iHibBaseDAO baseDAO = null;
    BlockDAO blockDAO = null;
    @Before
    public void init(){
        baseDAO = new HibBaseDAOImpl();
        blockDAO = new BlockDAOImpl();
    }

    @Test
    public void testFindById() {
        TBlock block = blockDAO.findById(1);
        System.out.println(block.getName());
    }

    @Test
    public void testFindByName() {
        System.out.println(blockDAO.findByName("石林风光旅行社"));
    }

    @Test
    public void findAllBlock() {
        List<TBlock> block = blockDAO.findAllBlock("", 1, 10);
        for(TBlock list: block){
            System.out.println(list.getName() + " " + list.getTime() + " " + list.getReason());
        }
    }

    @Test
    public void testFindAmount() {
        System.out.println(blockDAO.findAmount(""));
    }

    @Test
    public void testCreate() {
        TBlock block = new TBlock();
        block.setName("石林风光旅行社");
        block.setTime(DateUtil.tranDate2datetime(new Date()));
        block.setReason("故意提高价格");
        System.out.println(blockDAO.create(block));
    }

    @Test
    public void testDelete() {
        System.out.println(blockDAO.delete(2));
    }

    @Test
    public void testUpdate() {
        TBlock block = blockDAO.findById(6);
        block.setName("石林风光旅行社111");
        block.setTime(DateUtil.tranDate2datetime(new Date()));
        System.out.println(blockDAO.update(block));
    }
}
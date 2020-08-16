package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.ComplaintDAO;
import com.frontboy.model.TComplaint;
import com.frontboy.model.VComplaint;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComplaintDAOImplTest extends UserDAOImpl {

    iHibBaseDAO baseDAO = null;
    ComplaintDAO complaintDAO = null;
    @Before
    public void init(){
        baseDAO = new HibBaseDAOImpl();
        complaintDAO = new ComplaintDAOImpl();
    }

    @Test
    public void testFindById() {
        TComplaint complaint = complaintDAO.findById(1);
        System.out.println(complaint.getObject() + " " + complaint.getReason() + " " + complaint.getTime() + " "
        + complaint.getStatus() + " " + complaint.getResult() + " " + complaint.getOperator());
    }

    @Test
    public void findAllList() {
        List<TComplaint> complaints = complaintDAO.findAllList("", 1, 10);
        for (TComplaint list: complaints){
            System.out.println(list.getObject() + " " + list.getReason() + " " + list.getTime() + " "
                    + list.getStatus() + " " + list.getResult() + " " + list.getOperator());
        }
    }

    @Test
    public void findAllComplaint() {
        List<VComplaint> complaints = complaintDAO.findUserComplaints("xiaohong");
        for (VComplaint list: complaints){
            System.out.println(list.getObject() + " " + list.getReason() + " " + list.getTime() + " "
                    + list.getStatus() + " " + list.getResult() + " " + list.getOperator());
        }
    }

    @Test
    public void testFindAmount() {
        System.out.println(complaintDAO.findAmount(""));
    }

    @Test
    public void testCreate() {
        TComplaint complaint = new TComplaint();
        complaint.setObject("旅行社");
        complaint.setTime("2020-3-28");
        complaint.setReason("欺骗消费者");
        complaint.setStatus("处理中");
        complaint.setResult("。。。");
        complaint.setOperator("张三");
        System.out.println(complaintDAO.create(complaint));
    }

    @Test
    public void testDelete() {
        System.out.println(complaintDAO.delete(2));
    }

    @Test
    public void testUpdate() {
        TComplaint complaint = complaintDAO.findById(1);
        complaint.setObject("导游");
        complaint.setTime("2020-3-28");
        complaint.setReason("殴打消费者");
        complaint.setStatus("已处理");
        complaint.setResult("。。。");
        complaint.setOperator("李四");
        System.out.println(complaintDAO.update(complaint));
    }
}
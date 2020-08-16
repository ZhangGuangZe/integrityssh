package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.ComplaintDAO;
import com.frontboy.model.TComplaint;
import com.frontboy.model.VComplaint;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 投诉业务接口实现类
 */

@Component("complaintDAO")
public class ComplaintDAOImpl implements ComplaintDAO {

    private iHibBaseDAO baseDAO = null;
    public ComplaintDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public TComplaint findById(int id) {
        return (TComplaint) baseDAO.findById(TComplaint.class, id);
    }

    @Override
    public List<TComplaint> findAllList(String wherecondition, int currentPage, int pageSize) {
        String hql = "from TComplaint";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public List<VComplaint> findUserComplaints(String username) {
        String hql = "from VComplaint where username = ? order by id desc";
        Object[] para = { username };
        List<VComplaint> list = baseDAO.select(hql, para);
        return list;
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TComplaint ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public int create(TComplaint complaint) {
        Object obj = baseDAO.insert(complaint);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        TComplaint complaint = (TComplaint)baseDAO.findById(TComplaint.class, id);
        return baseDAO.delete(complaint);
    }

    @Override
    public boolean update(TComplaint complaint) {
        TComplaint tComplaint = (TComplaint)baseDAO.findById(TComplaint.class, complaint.getId());
        tComplaint.setObject(complaint.getObject());
        tComplaint.setReason(complaint.getReason());
        tComplaint.setTime(complaint.getTime());
        tComplaint.setResult(complaint.getResult());
        tComplaint.setStatus(complaint.getStatus());
        tComplaint.setOperator("景区管理员");
        return baseDAO.update(tComplaint);
    }
}

package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.RedBlockDAO;
import com.frontboy.model.TRedBlock;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 红黑榜业务接口实现类
 */

@Component("redBlockDAO")
public class RedBlockDAOImpl implements RedBlockDAO {

    private iHibBaseDAO baseDAO = null;
    public RedBlockDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public TRedBlock findById(int id) {
        return (TRedBlock) baseDAO.findById(TRedBlock.class, id);
    }

    @Override
    public List<TRedBlock> findAllList(String wherecondition, int currentPage, int pageSize) {
        String hql = "from TRedBlock";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public List<TRedBlock> findTimeList(String time) {
        String hql = "from TRedBlock where time = ?";
        Object[] para = { time };
        List<TRedBlock> list = baseDAO.select(hql, para);
        return list;
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TRedBlock ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public int create(TRedBlock rb) {
        Object obj = baseDAO.insert(rb);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        TRedBlock redBlock = (TRedBlock)baseDAO.findById(TRedBlock.class, id);
        return baseDAO.delete(redBlock);
    }

    @Override
    public boolean update(TRedBlock rb) {
        TRedBlock tRedBlock = (TRedBlock)baseDAO.findById(TRedBlock.class, rb.getId());
        tRedBlock.setType(rb.getType());
        tRedBlock.setPerson(rb.getPerson());
        tRedBlock.setTime(rb.getTime());
        tRedBlock.setReason(rb.getReason());
        return baseDAO.update(tRedBlock);
    }
}

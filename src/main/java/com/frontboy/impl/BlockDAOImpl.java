package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.BlockDAO;
import com.frontboy.model.TBlock;
import com.frontboy.model.TTravelagency;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 黑名单业务接口实现类
 */

@Component("blockDAO")
public class BlockDAOImpl implements BlockDAO {

    private iHibBaseDAO baseDAO = null;
    public BlockDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public TBlock findById(int id) {
        return (TBlock) baseDAO.findById(TBlock.class, id);
    }

    @Override
    public TBlock findByName(String name) {
        String hql = "from TBlock where name = ?";
        Object para[] = { name };
        List list = baseDAO.select(hql, para);
        if(list.size()>0)
            return (TBlock)list.get(0);
        else
            return null;
    }

    @Override
    public List<TBlock> findAllBlock(String wherecondition, int currentPage, int pageSize) {
        String hql = "from TBlock";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TBlock ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public int create(TBlock block) {
        Object obj = baseDAO.insert(block);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        TBlock block = (TBlock)baseDAO.findById(TBlock.class, id);
        return baseDAO.delete(block);
    }

    @Override
    public boolean update(TBlock block) {
        TBlock tBlock = (TBlock)baseDAO.findById(TBlock.class, block.getId());
        tBlock.setName(block.getName());
        tBlock.setReason(block.getReason());
        tBlock.setTime(block.getTime());
        return baseDAO.update(tBlock);
    }
}

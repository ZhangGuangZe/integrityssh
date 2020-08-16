package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.EvaluationDAO;
import com.frontboy.model.TEvaluation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评价业务接口实现类
 */

@Component("evaluationDAO")
public class EvaluationDAOImpl implements EvaluationDAO {

    private iHibBaseDAO baseDAO = null;
    public EvaluationDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public TEvaluation findById(int id) {
        return (TEvaluation) baseDAO.findById(TEvaluation.class, id);
    }

    @Override
    public List<TEvaluation> findAllList(String wherecondition, int currentPage, int pageSize) {
        String hql = "from TEvaluation";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TEvaluation ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public int create(TEvaluation te) {
        Object obj = baseDAO.insert(te);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        TEvaluation evaluation = (TEvaluation)baseDAO.findById(TEvaluation.class, id);
        return baseDAO.delete(evaluation);
    }

    @Override
    public boolean update(TEvaluation te) {
        TEvaluation evaluation = (TEvaluation)baseDAO.findById(TEvaluation.class, te.getId());
        evaluation.setTime(te.getTime());
        evaluation.setContent(te.getContent());
        evaluation.setStar(te.getStar());
        return baseDAO.update(evaluation);
    }
}

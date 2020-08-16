package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.AgencyDAO;
import com.frontboy.model.TTravelagency;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 旅行社业务接口实现类
 */
@Component("agencyDAO")
public class AgencyDAOImpl implements AgencyDAO {

    private iHibBaseDAO baseDAO = null;
    public AgencyDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public TTravelagency findById(long id) {
        return (TTravelagency) baseDAO.findById(TTravelagency.class, id);
    }

    @Override
    public TTravelagency findByName(String name) {
        String hql = "from TTravelagency where name = ?";
        Object para[] = { name };
        List list = baseDAO.select(hql, para);
        if(list.size()>0)
            return (TTravelagency)list.get(0);
        else
            return null;
    }

    @Override
    public List<TTravelagency> findAllAgency(String wherecondition, int currentPage, int pageSize) {
        String hql = "from TTravelagency";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TTravelagency ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public int create(TTravelagency agency) {
        Object obj = baseDAO.insert(agency);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(long id) {
        TTravelagency agency = (TTravelagency)baseDAO.findById(TTravelagency.class, id);
        return baseDAO.delete(agency);
    }

    @Override
    public boolean update(TTravelagency agency) {
        TTravelagency travel = (TTravelagency)baseDAO.findById(TTravelagency.class, agency.getId());
        travel.setName(agency.getName());
        travel.setAddress(agency.getAddress());
        travel.setTel(agency.getTel());
        travel.setIntroduction(agency.getIntroduction());
        travel.setPerson(agency.getPerson());
        travel.setStatus(agency.getStatus());
        return baseDAO.update(travel);
    }
}

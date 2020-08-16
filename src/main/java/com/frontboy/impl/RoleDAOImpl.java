package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.RoleDAO;
import com.frontboy.model.TRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色接口业务实现类
 */
@Component("roleDAO")
public class RoleDAOImpl implements RoleDAO {
    private iHibBaseDAO baseDAO = null;

    public RoleDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public TRole findById(long id) {
        return (TRole)baseDAO.findById(TRole.class, id);
    }

    @Override
    public TRole findByName(String name) {
        String hql = "from TRole where name = ?";
        Object para[] = { name };
        List list = baseDAO.select(hql, para);
        if(list.size()>0)
            return (TRole)list.get(0);
        else
            return null;
    }

    @Override
    public List<TRole> findAllRoles(String wherecondition,
                                    int currentPage, int pageSize) {
        String hql = "from TRole";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TRole ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public int create(TRole role) {
        Object obj = baseDAO.insert(role);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(long id) {
        TRole role = (TRole)baseDAO.findById(TRole.class, id);
        return baseDAO.delete(role);
    }

    @Override
    public boolean update(TRole role) {
        TRole roles = (TRole) baseDAO.findById(TRole.class, role.getId());
        roles.setName(role.getName());
        roles.setDescription(role.getDescription());
        return baseDAO.update(roles);
    }
}

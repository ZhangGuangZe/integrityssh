package com.frontboy.impl;

import com.frontboy.basic.HibBaseDAOImpl;
import com.frontboy.basic.iHibBaseDAO;
import com.frontboy.dao.UserDAO;
import com.frontboy.model.TUser;
import com.frontboy.model.VUserRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户接口实现类
 */
@Component("userDAO")
public class UserDAOImpl implements UserDAO {

    private iHibBaseDAO baseDAO = null;

    public UserDAOImpl() {
        baseDAO = new HibBaseDAOImpl();
    }

    @Override
    public int create(TUser user) {
        Object obj = baseDAO.insert(user);
        if(obj!=null){
            return 1;
        }
        return 0;
    }

    @Override
    public boolean delete(String username) {
        TUser user = (TUser)baseDAO.findById(TUser.class, username);
        return baseDAO.delete(user);
    }

    @Override
    public boolean update(TUser user) {
        TUser users = (TUser) baseDAO.findById(TUser.class, user.getUsername());
        users.setUsername(user.getUsername());
        users.setPassword(user.getPassword());
        users.setPhone(user.getPhone());
        users.setRealname(user.getRealname());
        users.setGender(user.getGender());
        users.setLevel(user.getLevel());
        users.setLanguage(user.getLanguage());
        users.setEnabled(user.getEnabled());
        return baseDAO.update(users);
    }

    @Override
    public boolean editUser(TUser user) {
        TUser users = (TUser) baseDAO.findById(TUser.class, user.getUsername());
        users.setPhone(user.getPhone());
        users.setGender(user.getGender());
        return baseDAO.update(users);
    }

    @Override
    public boolean updatePass(String username, String password) {
        TUser user = (TUser) baseDAO.findById(TUser.class, username);
        user.setPassword(password);
        return baseDAO.update(user);
    }

    @Override
    public VUserRole login(String username, String password) {
        String hql = "from VUserRole where username = ? and password = ?";
        Object [] para = {username, password};
        List list = baseDAO.select(hql, para);
        if(list.size()>0)
            return (VUserRole)list.get(0);
        else
            return null;
    }

    @Override
    public TUser findById(String username) {
        return (TUser)baseDAO.findById(TUser.class, username);
    }

    @Override
    public List<TUser> findAllUser(String wherecondition,
                                   int currentPage, int pageSize) {
        String hql = "from TUser";
        if(wherecondition != null && !wherecondition.equals("")){
            hql += wherecondition;
        }
        return baseDAO.selectByPage(hql, currentPage, pageSize);
    }

    @Override
    public int findAmount(String wherecondition) {
        String hql = "select count(*) from TUser ";
        if (wherecondition != null && !wherecondition.equals("")) {
            hql += wherecondition;
        }
        return baseDAO.selectValue(hql);
    }

    @Override
    public boolean updateToken(String username, String token) {
        TUser user = (TUser) baseDAO.findById(TUser.class, username);
        user.setToken(token);
        return baseDAO.update(user);
    }

    @Override
    public VUserRole getToken(String token) {
        String hql = "from VUserRole where token = ?";
        Object [] para = {token};
        List list = baseDAO.select(hql, para);
        if(list.size()>0)
            return (VUserRole)list.get(0);
        else
            return null;
    }

}

package com.frontboy.dao;

import com.frontboy.model.TUser;
import com.frontboy.model.VUserRole;

import java.util.List;

/**
 * 用户业务接口
 */

public interface UserDAO {

    /**
     * 添加一个用户
     * @param user
     * @return
     */
    public int create(TUser user);

    /**
     * 删除一个用户
     * @param username
     * @return
     */
    public boolean delete(String username);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean update(TUser user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean editUser(TUser user);

    public boolean updatePass(String username, String password);
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public VUserRole login(String username, String password);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    public TUser findById(String username);

    /**
     * 获取所有用户
     * @param wherecondition
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<TUser> findAllUser(String wherecondition,
                                    int currentPage, int pageSize);

    /**
     * 根据条件获取符合条件的用户的数量
     * @param wherecondition
     * @return
     */
    public int findAmount(String wherecondition);

    /**
     * 修改用户 token
     * @param username
     * @param token
     * @return
     */
    public boolean updateToken(String username, String token);

    /**
     * 根据用户token获取用户信息
     * @param token
     * @return
     */
    public VUserRole getToken(String token);
}

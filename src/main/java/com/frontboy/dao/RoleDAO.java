package com.frontboy.dao;

import com.frontboy.model.TRole;

import java.util.List;

/**
 * 角色业务接口
 */
public interface RoleDAO {
    /**
     * 根据角色id查询
     * @param id
     * @return
     */
    public TRole findById(long id);

    /**
     * 根据角色名称查询
     * @param name
     * @return
     */
    public TRole findByName(String name);

    /**
     * 查询所有角色
     * @return
     */
    public List<TRole> findAllRoles(String wherecondition, int currentPage, int pageSize);

    /**
     * 获取角色数量
     * @return
     */
    public int  findAmount(String wherecondition);

    /**
     * 添加一个角色
     * @param role
     * @return
     */
    public int create(TRole role);

    /**
     * 删除一个角色
     * @param id
     * @return
     */
    public boolean delete(long id);

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    public boolean update(TRole role);
}
